package org.likelion.newsfactbackend.domain.news.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode; // HTTP 상태 코드
    private String field;
    private String message; // 에러 메시지
}
