package org.likelion.newsfactbackend.news.mk.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MkNewsResponseDto {
    private String company;
    private String title;
    private String date;
    private String img;
    private String content;
    private String writer;
    private String writerEmail;
    private String keyword;
}

