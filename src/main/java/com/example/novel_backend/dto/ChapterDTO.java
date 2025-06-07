package com.example.novel_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO {
    private Long id;
    private String title;
    private Integer chapterNumber;
    private Long views;
} 