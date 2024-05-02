package org.likelion.newsfactbackend.deletescraps.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DeleteScrapsRequestDto {
    private int code;
    private String msg;
}
