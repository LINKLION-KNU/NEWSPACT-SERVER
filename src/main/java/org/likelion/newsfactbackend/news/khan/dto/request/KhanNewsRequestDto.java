package org.likelion.newsfactbackend.news.khan.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class KhanNewsRequestDto {
    private String search;
}
