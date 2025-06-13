package com.example.novel_backend.controller;

import com.example.novel_backend.dto.ChapterContentDTO;
import com.example.novel_backend.entities.Chapter;
import com.example.novel_backend.mapper.ChapterContentMapper;
import com.example.novel_backend.services.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;
    private final ChapterContentMapper chapterContentMapper;

    @GetMapping("/novels/{novelId}/chapters/{chapterId}")
    public ResponseEntity<ChapterContentDTO> getChapterContent(
            @PathVariable Long novelId,
            @PathVariable Long chapterId) {
        // Lấy thông tin chapter
        Chapter chapter = chapterService.getChapterById(novelId, chapterId);
        
        // Tăng lượt xem
        chapterService.incrementViews(chapterId);
        
        // Map sang DTO và trả về
        ChapterContentDTO chapterContent = chapterContentMapper.toDTO(chapter);
        return ResponseEntity.ok(chapterContent);
    }
} 