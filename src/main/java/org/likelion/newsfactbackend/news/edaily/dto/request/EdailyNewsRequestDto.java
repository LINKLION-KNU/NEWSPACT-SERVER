package org.likelion.newsfactbackend.news.edaily.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EdailyNewsRequestDto {
    private String search;
}
