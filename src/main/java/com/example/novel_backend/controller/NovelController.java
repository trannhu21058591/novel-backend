package com.example.novel_backend.controller;

import com.example.novel_backend.dto.NovelSummaryDTO;
import com.example.novel_backend.dto.NovelWithChaptersDTO;
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
    public ResponseEntity<List<NovelSummaryDTO>> getAllNovels() {
        List<Novel> novels = novelService.getAllNovels();
        List<NovelSummaryDTO> dtoList = novels.stream()
                .filter(novel -> !novel.isDeleted()) // lọc nếu cần
                .map(novelMapper::toSummaryDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/novels/{id}")
    public ResponseEntity<NovelWithChaptersDTO> getNovelById(@PathVariable Long id) {
        Novel novel = novelService.getNovelById(id);
        if (novel.isDeleted()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(novelMapper.toDTOWithChapters(novel));
    }

    @GetMapping("/search")
    public ResponseEntity<List<NovelWithChaptersDTO>> searchNovels(@RequestParam String keyword) {
        List<Novel> novels = novelService.searchByTitle(keyword);
        List<NovelWithChaptersDTO> novelDTOs = novels.stream()
                .map(novelMapper::toDTOWithChapters)
                .collect(Collectors.toList());
        return ResponseEntity.ok(novelDTOs);
    }
}
