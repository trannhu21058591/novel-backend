package com.example.novel_backend.services;

import com.example.novel_backend.dao.ChapterRepository;
import com.example.novel_backend.dao.NovelRepository;
import com.example.novel_backend.entities.Chapter;
import com.example.novel_backend.entities.Novel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final NovelRepository novelRepository;

    @Transactional(readOnly = true)
    public Chapter getChapterById(Long novelId, Long chapterId) {
        Novel novel = novelRepository.findById(novelId)
                .orElseThrow(() -> new RuntimeException("Novel not found"));

        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        // Kiểm tra chapter có thuộc novel không
        if (!chapter.getNovel().getId().equals(novelId)) {
            throw new RuntimeException("Chapter does not belong to this novel");
        }

        return chapter;
    }

    @Transactional
    public void incrementViews(Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
        chapter.setViews(chapter.getViews() + 1);
        chapterRepository.save(chapter);
    }
} 