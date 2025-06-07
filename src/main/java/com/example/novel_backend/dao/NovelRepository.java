package com.example.novel_backend.dao;

import com.example.novel_backend.entities.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, Long> {
    List<Novel> findByTitleContainingIgnoreCase(String keyword);
}
