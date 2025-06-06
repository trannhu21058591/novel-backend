package com.example.novel_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chapters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Lob
    @Column(nullable = false)
    private String content;

    @NotNull
    @Column(name = "chapter_number", nullable = false)
    private Integer chapterNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id", nullable = false)
    private Novel novel;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
