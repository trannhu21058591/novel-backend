package com.example.novel_backend.mapper;

import com.example.novel_backend.dto.ChapterDTO;
import com.example.novel_backend.dto.NovelDTO;
import com.example.novel_backend.entities.Novel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NovelMapper {

    public NovelDTO toNovelDTO(Novel novel) {
        if (novel == null) return null;

        List<ChapterDTO> chapters = novel.getChapters() != null
                ? novel.getChapters().stream()
                .map(chapter -> ChapterDTO.builder()
                        .id(chapter.getId())
                        .title(chapter.getTitle())
                        .createdAt(chapter.getCreatedAt())
                        .chapterNumber(chapter.getChapterNumber())
                        .views(chapter.getViews())
                        .build())
                .collect(Collectors.toList())
                : null;

        long totalViews = chapters != null
                ? chapters.stream().mapToLong(ChapterDTO::getViews).sum()
                : 0;

        long totalChapters = chapters != null ? chapters.size() : 0;

        return NovelDTO.builder()
                .id(novel.getId())
                .title(novel.getTitle())
                .description(novel.getDescription())
                .deleted(novel.isDeleted())
                .status(novel.getStatus())
                .createdAt(novel.getCreatedAt())

                .authorId(novel.getAuthor() != null ? novel.getAuthor().getId() : null)
                .authorName(novel.getAuthor() != null ? novel.getAuthor().getProfileName() : null)

                .coverImageId(novel.getCoverImage() != null ? novel.getCoverImage().getId() : null)
                .coverImageBase64(novel.getCoverImage() != null ? novel.getCoverImage().getData() : null)

                .tags(novel.getTags() != null
                        ? novel.getTags().stream().map(tag -> tag.getName()).toArray(String[]::new)
                        : new String[0])

                .chapters(chapters)
                .totalViews(totalViews)
                .totalChapters(totalChapters)
                .build();
    }
}
