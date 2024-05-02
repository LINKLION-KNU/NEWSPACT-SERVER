package org.likelion.newsfactbackend.news.newsis.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsisNewsRequestDto {
    private String search;
}
