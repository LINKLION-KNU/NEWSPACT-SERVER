package org.likelion.newsfactbackend.domain.news.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestNewsDto {
    private String keyWord;
    private String company;
}
