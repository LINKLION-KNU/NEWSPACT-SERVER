package org.likelion.newsfactbackend.domain.auth.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestSignInDto {
    private String email;
    private String password;
}
