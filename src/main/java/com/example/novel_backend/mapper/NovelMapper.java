package com.example.novel_backend.mapper;

import com.example.novel_backend.dto.NovelSummaryDTO;
import com.example.novel_backend.dto.NovelWithChaptersDTO;
import com.example.novel_backend.dto.ChapterDTO;
import com.example.novel_backend.entities.Novel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NovelMapper {
    
    public NovelWithChaptersDTO toDTOWithChapters(Novel novel) {
        if (novel == null) {
            return null;
        }
        
        List<ChapterDTO> chapters = novel.getChapters() != null ? 
            novel.getChapters().stream()
                .map(chapter -> ChapterDTO.builder()
                    .id(chapter.getId())
                    .title(chapter.getTitle())
                    .chapterNumber(chapter.getChapterNumber())
                    .views(chapter.getViews())
                    .build())
                .collect(Collectors.toList())
            : null;
            
        // Tính tổng lượt xem từ các chapter
        Long totalViews = chapters != null ? 
            chapters.stream()
                .mapToLong(ChapterDTO::getViews)
                .sum()
            : 0L;
            
        // Tính tổng số chương
        Integer totalChapters = chapters != null ? chapters.size() : 0;
        
        return NovelWithChaptersDTO.builder()
                .id(novel.getId())
                .title(novel.getTitle())
                .description(novel.getDescription())
                .deleted(novel.isDeleted())
                .status(novel.getStatus())
                .createdAt(novel.getCreatedAt())
                .authorId(novel.getAuthor() != null ? novel.getAuthor().getId() : null)
                .authorName(novel.getAuthor() != null ? novel.getAuthor().getProfileName() : null)
                .coverImageId(novel.getCoverImage() != null ? novel.getCoverImage().getId() : null)
                .chapters(chapters)
                .totalViews(totalViews)
                .totalChapters(totalChapters)
                .build();
    }

    public NovelSummaryDTO toSummaryDTO(Novel novel) {
        if (novel == null) {
            return null;
        }

        long totalViews = novel.getChapters() != null
                ? novel.getChapters().stream().mapToLong(ch -> ch.getViews()).sum()
                : 0;

        long totalChapters = novel.getChapters() != null
                ? novel.getChapters().size()
                : 0;

        return NovelSummaryDTO.builder()
                .id(novel.getId())
                .title(novel.getTitle())
                .coverImageBase64(novel.getCoverImage() != null ? novel.getCoverImage().getData() : null)
                .totalViews(totalViews)
                .totalChapters(totalChapters)
                .isDeleted(novel.isDeleted())
                .status(novel.getStatus())
                .createdAt(novel.getCreatedAt())
                .authorId(novel.getAuthor() != null ? novel.getAuthor().getId() : null)
                .authorName(novel.getAuthor() != null ? novel.getAuthor().getProfileName() : null)
                .tags(novel.getTags() != null ? 
                    novel.getTags().stream()
                        .map(tag -> tag.getName())
                        .toArray(String[]::new) 
                    : new String[0])
                .build();
    }
} 