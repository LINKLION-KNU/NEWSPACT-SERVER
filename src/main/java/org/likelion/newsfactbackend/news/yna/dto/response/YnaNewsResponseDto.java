package org.likelion.newsfactbackend.news.yna.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class YnaNewsResponseDto {

    private String company;
    private String title;
    private String date;
    private String img;
    private String content;
    private String writer;
    private String writerEmail;
    private String keyword;

}
