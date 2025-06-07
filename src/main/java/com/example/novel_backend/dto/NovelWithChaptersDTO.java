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
public class NovelWithChaptersDTO {
    private Long id;
    private String title;
    private String description;
    private boolean deleted;
    private NovelStatus status;
    private LocalDateTime createdAt;
    
    // Author information
    private Long authorId;
    private String authorName;
    
    // Cover image
    private Long coverImageId;
    
    // Chapter information
    private List<ChapterDTO> chapters;
    
    // Total views (calculated from chapters)
    private Long totalViews;
    
    // Total chapters
    private Integer totalChapters;
} 