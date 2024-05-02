package org.likelion.newsfactbackend.news.mk.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MkNewsRequestDto {
    private String search;
}
