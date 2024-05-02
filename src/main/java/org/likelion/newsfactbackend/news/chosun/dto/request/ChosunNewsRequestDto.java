package org.likelion.newsfactbackend.news.chosun.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ChosunNewsRequestDto {
    private String search;
}
