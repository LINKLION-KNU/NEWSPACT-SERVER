package org.likelion.newsfactbackend.domain.news.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Keyword {
    String keywordName;

    public Boolean isOver() {
        return keywordName.length() < 255;
    }
}
