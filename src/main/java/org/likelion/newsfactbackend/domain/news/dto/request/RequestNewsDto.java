package org.likelion.newsfactbackend.domain.news.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class RequestNewsDto {

    private String keyword; // 키워드(검색어)
    private Long size; // 4/10 형태
    public RequestNewsDto(Long size, String keyword) {
        this.size = size;
        this.keyword = keyword;
    }

    // Getters and Setters
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}