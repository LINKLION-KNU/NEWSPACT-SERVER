package org.likelion.newsfactbackend.news.mt.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MtNewsRequestDto {
    private String search;
}
