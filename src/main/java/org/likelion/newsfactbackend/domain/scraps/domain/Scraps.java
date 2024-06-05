package org.likelion.newsfactbackend.domain.scraps.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Scraps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String company;

    @Column(nullable = false, unique = false)
    private String title;

    @Column(nullable = false, unique = false)
    private String date;

    @Column(nullable = false, unique = false)
    private String img;

    @Column(nullable = false, unique = false)
    private String content;

    @Column(nullable = false, unique = false)
    private String writer;

    @Column(nullable = false, unique = false)
    private String writerEmail;

    @Column(nullable = false, unique = false)
    private String keyword;

}
