package org.likelion.newsfactbackend.domain.news.dto.response;

import com.sun.jna.platform.win32.WinRas;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.news.exception.NewsException;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class ResponseNewsDto {

    @Builder.Default
    private String companyLogo = "/images/default_logo.png"; // 로고
    private String company = "미상"; // 언론사 이름
    private String title;
    private String subTitle = "내용 없음";
    private String thumbnail = "/images/default_thumbnail.jpg"; // 썸네일
    private String publishDate = LocalDate.now().toString(); // 발행일자
    private String newsUrl; // URL

    public ResponseNewsDto(String companyLogo, String company, String title, String subTitle, String thumbnail, String publishDate, String newsUrl) {
        this.companyLogo = companyLogo;
        this.company = company;
        this.title = title;
        this.subTitle = subTitle;
        this.thumbnail = thumbnail;
        this.publishDate = publishDate;
        this.newsUrl = newsUrl;
    }

    public boolean isTitleEmpty() {
        return title.isEmpty();
    }

    public void titleDefault(String keyword) {
        title = keyword + "관련 기사입니다.";

    }

    public void validateFields() {
        if (newsUrl.isEmpty()) {
            //log.warn("뉴스 링크 값이 존재하지않습니다.");
            throw new NewsException("newsUrl", "뉴스 링크 값이 존재하지않습니다.");
        }

/*    public void validateFields() {
        if (company.isEmpty()) {
            throw new NewsException("company", "언론사 값이 존재하지 않습니다.");
        }
        if (subTitle.isEmpty()) {
            throw new NewsException("subTitle", "내용이 존재하지않습니다.");
        }
        if (thumbnail.isEmpty()) {
            //log.warn("썸네일 값이 존재하지않습니다.");
            throw new NewsException("thumbnail", "썸네일 값이 존재하지않습니다.");
        }
        if (newsUrl.isEmpty()) {
            //log.warn("뉴스 링크 값이 존재하지않습니다.");
            throw new NewsException("newsUrl", "뉴스 링크 값이 존재하지않습니다.");
        }
        if (publishDate.isEmpty()) {
            //log.warn("발행일 값이 존재하지않습니다.");
            throw new NewsException("publishDate", "발행일 값이 존재하지않습니다.");
        }*/



    /*
    public void validateFields(String keyword) {
        if (company.isEmpty()) {
            isDefault(keyword);
        }

        if (title.isEmpty()) {
            //log.warn("제목 값이 존재하지않습니다.");
            throw new NewsException("title", "제목 값이 존재하지않습니다.");
        }
        if (subTitle.isEmpty()) {
            throw new NewsException("subTitle", "내용이 존재하지않습니다.");
        }
        if (thumbnail.isEmpty()) {
            //log.warn("썸네일 값이 존재하지않습니다.");
            throw new NewsException("thumbnail", "썸네일 값이 존재하지않습니다.");
        }
        if (newsUrl.isEmpty()) {
            //log.warn("뉴스 링크 값이 존재하지않습니다.");
            throw new NewsException("newsUrl", "뉴스 링크 값이 존재하지않습니다.");
        }
        if (publishDate.isEmpty()) {
            //log.warn("발행일 값이 존재하지않습니다.");
            throw new NewsException("publishDate", "발행일 값이 존재하지않습니다.");
        }
    */


    }


}
