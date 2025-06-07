package com.example.novel_backend.mapper;

import com.example.novel_backend.dto.NovelDTO;
import com.example.novel_backend.entities.Novel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NovelMapper {
    
    public NovelDTO toDTO(Novel novel) {
        if (novel == null) {
            return null;
        }
        
        return NovelDTO.builder()
                .id(novel.getId())
                .title(novel.getTitle())
                .description(novel.getDescription())
                .views(novel.getViews())
                .isDeleted(novel.isDeleted())
                .status(novel.getStatus())
                .createdAt(novel.getCreatedAt())
                .authorId(novel.getAuthor() != null ? novel.getAuthor().getId() : null)
                .authorName(novel.getAuthor() != null ? novel.getAuthor().getProfileName() : null)
                .coverImageId(novel.getCoverImage() != null ? novel.getCoverImage().getId() : null)
                .tags(novel.getTags() != null ? 
                    novel.getTags().stream()
                        .map(tag -> tag.getName())
                        .toArray(String[]::new) 
                    : new String[0])
                .build();
    }
} 