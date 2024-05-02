package org.likelion.newsfactbackend.scraps.dto.response;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScrapsNewsResponseDto {
    private String company;
    private String title;
    private String date;
    private String img;
    private String content;
    private String writer;
    private String writerEmail;
    private String keyword;
}
