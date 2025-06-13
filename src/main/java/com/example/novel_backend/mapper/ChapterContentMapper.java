package com.example.novel_backend.mapper;

import com.example.novel_backend.dto.ChapterContentDTO;
import com.example.novel_backend.dto.CommentContentDTO;
import com.example.novel_backend.dto.ParagraphContentDTO;
import com.example.novel_backend.entities.Chapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChapterContentMapper {
    
    public ChapterContentDTO toDTO(Chapter chapter) {
        if (chapter == null) {
            return null;
        }

        List<ParagraphContentDTO> paragraphs = chapter.getParagraphs() != null ?
            chapter.getParagraphs().stream()
                .map(paragraph -> ParagraphContentDTO.builder()
                    .id(paragraph.getId())
                    .content(paragraph.getContent())
                    .paragraphIndex(paragraph.getParagraphIndex())
                    .comments(paragraph.getComments() != null ?
                        paragraph.getComments().stream()
                            .map(comment -> CommentContentDTO.builder()
                                .id(comment.getId())
                                .content(comment.getContent())
                                .userId(comment.getUser().getId())
                                .userName(comment.getUser().getProfileName())
                                .createdAt(comment.getCreatedAt())
                                .build())
                            .collect(Collectors.toList())
                        : null)
                    .build())
                .collect(Collectors.toList())
            : null;

        return ChapterContentDTO.builder()
                .id(chapter.getId())
                .title(chapter.getTitle())
                .content(chapter.getContent())
                .chapterNumber(chapter.getChapterNumber())
                .views(chapter.getViews())
                .createdAt(chapter.getCreatedAt())
                .novelId(chapter.getNovel().getId())
                .novelTitle(chapter.getNovel().getTitle())
                .paragraphs(paragraphs)
                .build();
    }
} 