package com.example.novel_backend.dto;

import com.example.novel_backend.Enum.NovelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NovelDTO {
    private Long id;
    private String title;
    private String description;
    private Long views;
    private boolean isDeleted;
    private NovelStatus status;
    private LocalDateTime createdAt;
    
    // Author information
    private Long authorId;
    private String authorName;
    
    // Cover image information
    private Long coverImageId;
    
    // Tags
    private String[] tags;
} 