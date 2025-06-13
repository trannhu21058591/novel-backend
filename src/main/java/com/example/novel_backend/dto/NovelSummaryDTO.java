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
@AllArgsConstructor
@NoArgsConstructor
public class NovelSummaryDTO {
    private Long id;
    private String title;
    private String coverImageBase64;
    private Long totalViews;
    private Long totalChapters;
    private boolean isDeleted;
    private NovelStatus status;
    private LocalDateTime createdAt;
    // Author information
    private Long authorId;
    private String authorName;
    
    // Tags
    private String[] tags;
}
