package org.likelion.newsfactbackend.news.yna.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class YnaNewsRequestDto {
    private String search;
}
