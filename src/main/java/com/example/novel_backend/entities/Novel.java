package com.example.novel_backend.entities;

import com.example.novel_backend.Enum.NovelStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "novels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private NovelStatus status;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_image_id")
    private Image coverImage;

    @ManyToMany
    @JoinTable(
            name = "novel_tags",
            joinColumns = @JoinColumn(name = "novel_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
    private List<ReadingHistory> readingHistories;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
