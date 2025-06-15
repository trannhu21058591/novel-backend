package com.example.novel_backend.controller;

import com.example.novel_backend.dto.NovelDTO;

import com.example.novel_backend.entities.Novel;
import com.example.novel_backend.mapper.NovelMapper;
import com.example.novel_backend.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NovelController {
    private final NovelService novelService;
    private final NovelMapper novelMapper;

    @GetMapping("/novels")
    public ResponseEntity<List<NovelDTO>> getAllNovels() {
        List<Novel> novels = novelService.getAllNovels();
        List<NovelDTO> dtoList = novels.stream()
                .filter(novel -> !novel.isDeleted())
                .map(novelMapper::toNovelDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/novels/{id}")
    public ResponseEntity<NovelDTO> getNovelById(@PathVariable Long id) {
        Novel novel = novelService.getNovelById(id);
        if (novel.isDeleted()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(novelMapper.toNovelDTO(novel));
    }

    @GetMapping("/search")
    public ResponseEntity<List<NovelDTO>> searchNovels(@RequestParam String keyword) {
        List<Novel> novels = novelService.searchByTitle(keyword);
        List<NovelDTO> novelDTOs = novels.stream()
                .map(novelMapper::toNovelDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(novelDTOs);
    }
}
