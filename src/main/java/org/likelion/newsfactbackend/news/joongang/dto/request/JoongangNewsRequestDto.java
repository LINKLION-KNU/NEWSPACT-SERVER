package org.likelion.newsfactbackend.news.joongang.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JoongangNewsRequestDto {
    private String search;
}
