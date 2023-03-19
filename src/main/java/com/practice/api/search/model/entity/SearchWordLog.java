package com.practice.api.search.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "search_word_log", indexes = @Index(name = "idx_search_word", columnList = "search_word"))
public class SearchWordLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_word_log_uid")
    private Long searchWordLogUid;

    @Column(name = "search_word", nullable = false)
    private String searchWord;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
}
