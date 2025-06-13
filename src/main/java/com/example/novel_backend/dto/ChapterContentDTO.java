package com.example.novel_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterContentDTO {
    private Long id;
    private String title;
    private String content;
    private Integer chapterNumber;
    private Long views;
    private LocalDateTime createdAt;

    // Novel information
    private Long novelId;
    private String novelTitle;

    // Paragraphs with comments
    private List<ParagraphContentDTO> paragraphs;
}

