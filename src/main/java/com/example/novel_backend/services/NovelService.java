package com.example.novel_backend.services;

import com.example.novel_backend.dao.NovelRepository;
import com.example.novel_backend.entities.Novel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NovelService {
    private final NovelRepository novelRepository;

    public List<Novel> searchByTitle(String keyword) {
        return novelRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Novel> getAllNovels() {
        return novelRepository.findAll();
    }
}
