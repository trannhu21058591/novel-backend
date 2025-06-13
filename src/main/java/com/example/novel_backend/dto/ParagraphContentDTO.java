package com.example.novel_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParagraphContentDTO {
    private Long id;
    private String content;
    private Integer paragraphIndex;
    private List<CommentContentDTO> comments;
}