package org.likelion.newsfactbackend.scraps.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScrapsNewsRequestDto {
    private String page;
    private String size;
}
