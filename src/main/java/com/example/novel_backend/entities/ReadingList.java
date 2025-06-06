package com.example.novel_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reading_lists")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "readinglist_novels",
            joinColumns = @JoinColumn(name = "readinglist_id"),
            inverseJoinColumns = @JoinColumn(name = "novel_id")
    )
    private List<Novel> novels;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
