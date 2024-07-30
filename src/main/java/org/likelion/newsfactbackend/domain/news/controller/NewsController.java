package org.likelion.newsfactbackend.domain.news.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.news.dto.RecommendNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.request.PageRequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.likelion.newsfactbackend.domain.news.exception.ErrorCode;
import org.likelion.newsfactbackend.domain.news.exception.NewsException;
import org.likelion.newsfactbackend.domain.news.service.NewsService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    @Operation(summary = "키워드 입력 시, 관련 뉴스 기사를 반환합니다.")
    @GetMapping("/search") //http://localhost:8080/api/v1/news/search?keyword=%ED%8A%B8%EB%9F%BC%ED%94%84&size=10
    public ResponseEntity<Page<ResponseNewsDto>> searchNews(@RequestParam(defaultValue = "4") Long size, @RequestParam String keyword) throws IOException {
        PageRequestNewsDto newsRequestDto = new PageRequestNewsDto(size, keyword);

        if (isNull(newsRequestDto.getKeyword())) {
            throw new NewsException(ErrorCode.EMPTY_KEYWORD);
        }
        if (newsRequestDto.isOverSize(size)) {
            throw new NewsException(ErrorCode.EMPTY_SIZE);
        }

        Page<ResponseNewsDto> responseNewsDtoPage = newsService.searchNews(newsRequestDto);
        List<ResponseNewsDto> content = responseNewsDtoPage.getContent();

        for (ResponseNewsDto news : content) {
            if (news.isTitleEmpty()) {
                news.titleDefault(keyword);
                log.warn("{} title 값이 없습니다.", news);
            }
            news.validateFields();
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseNewsDtoPage);
    }

    /*https://newspect.co.kr/api/v1/news-details?url=${url}*/

    @Operation(summary = "뉴스 상세페이지를 반환합니다.")
    @GetMapping("/news-details")
    public ResponseEntity<?> getNews(@RequestParam String url, @RequestParam String oid) throws IOException {

        if (isNull(oid)) {
            throw new NewsException(ErrorCode.NOT_FOUND_OID);
        }
        if (isNull(url)) {
            throw new NewsException(ErrorCode.NOT_FOUND_NEWS_URL);
        }
        ResponseNewsDto responseNewsDto = newsService.fetchNewsArticleDetails(url, oid);
        return ResponseEntity.status(HttpStatus.OK).body(responseNewsDto);
    }

    private boolean isNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    @GetMapping("/search/recommend")
    public List<RecommendNewsDto> recommendNews(@RequestParam String keyword) throws IOException {
        List<ResponseNewsDto> allArticles = newsService.fetchAllNewsArticles(keyword);
        return newsService.getRecommendedArticles(allArticles);
    }
}
