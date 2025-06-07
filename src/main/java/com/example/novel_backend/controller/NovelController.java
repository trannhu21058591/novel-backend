package com.example.novel_backend.controller;

import com.example.novel_backend.dto.NovelWithChaptersDTO;
import com.example.novel_backend.entities.Novel;
import com.example.novel_backend.mapper.NovelMapper;
import com.example.novel_backend.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/novels")
@RequiredArgsConstructor
public class NovelController {
    private final NovelService novelService;
    private final NovelMapper novelMapper;

    @GetMapping
    public ResponseEntity<List<NovelWithChaptersDTO>> getAllNovels() {
        List<Novel> novels = novelService.getAllNovels();
        List<NovelWithChaptersDTO> novelDTOs = novels.stream()
                .map(novelMapper::toDTOWithChapters)
                .collect(Collectors.toList());
        return ResponseEntity.ok(novelDTOs);
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
