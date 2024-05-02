package org.likelion.newsfactbackend.keywords.dto.response;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordsResponseDto {
    private String rank;
    private String keyword;
    private String count;
}
