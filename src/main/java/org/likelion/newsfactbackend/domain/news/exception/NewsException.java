package org.likelion.newsfactbackend.domain.news.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsException extends NullPointerException {
    private String field;
    private String message;
}
