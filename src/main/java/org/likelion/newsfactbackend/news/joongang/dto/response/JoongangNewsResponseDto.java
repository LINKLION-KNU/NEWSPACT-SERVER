package org.likelion.newsfactbackend.news.joongang.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JoongangNewsResponseDto {
    private String company;
    private String title;
    private String date;
    private String img;
    private String content;
    private String writer;
    private String writerEmail;
    private String keyword;
}
