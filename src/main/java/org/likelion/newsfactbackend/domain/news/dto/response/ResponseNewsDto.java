package org.likelion.newsfactbackend.domain.news.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseNewsDto {
    private String companyLogo;
    private String company;
    private String title;
    private String subTitle; // 내용
    private String thumbnail; // 썸네일
    private String publishDate;
    private String newsUrl;

}
