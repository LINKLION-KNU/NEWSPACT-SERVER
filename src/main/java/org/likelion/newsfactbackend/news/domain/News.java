package org.likelion.newsfactbackend.news.domain;

import jakarta.persistence.*;
import lombok.*;
import org.likelion.newsfactbackend.global.domain.BaseTimeEntity;
import org.likelion.newsfactbackend.keyword.domain.TrendKeyword;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table
public class News extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String img;

    @Column(nullable = false, unique = true)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false, unique = true)
    private String writerEmail;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "trendKeyword_id")
    private TrendKeyword trendKeyword;

}
