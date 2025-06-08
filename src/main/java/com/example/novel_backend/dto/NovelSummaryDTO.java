package com.example.novel_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
