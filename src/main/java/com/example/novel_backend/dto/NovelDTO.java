package com.example.novel_backend.dto;

import com.example.novel_backend.Enum.NovelStatus;
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
public class NovelDTO {
    private Long id;
    private String title;
    private String description;

    private boolean deleted;
    private NovelStatus status;
    private LocalDateTime createdAt;

    // Author info
    private Long authorId;
    private String authorName;

    // Cover image
    private Long coverImageId;
    private String coverImageBase64;

    // Tags
    private String[] tags;

    // Chapter info
    private List<ChapterDTO> chapters;
    private Long totalViews;
    private Long totalChapters;
}
