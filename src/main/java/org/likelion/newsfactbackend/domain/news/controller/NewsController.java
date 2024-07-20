package org.likelion.newsfactbackend.domain.news.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.newsfactbackend.domain.news.dto.request.PageRequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.likelion.newsfactbackend.domain.news.exception.NewsException;
import org.likelion.newsfactbackend.domain.news.service.NewsService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/search") //http://localhost:8080/api/v1/news/search?keyword=%ED%8A%B8%EB%9F%BC%ED%94%84&size=10
    public ResponseEntity<Page<ResponseNewsDto>> searchNews(@RequestParam(defaultValue = "4") Long size, @RequestParam String keyword) throws IOException {
        PageRequestNewsDto newsRequestDto = new PageRequestNewsDto(size, keyword);


        if (newsRequestDto.getKeyword().isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("검색어를 입력해주세요.");
        }
        if (newsRequestDto.isOverSize(size)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Size 값이 ..~~~");
        }

        Page<ResponseNewsDto> responseNewsDtoPage = newsService.searchNews(newsRequestDto);
        List<ResponseNewsDto> content = responseNewsDtoPage.getContent();

        for (ResponseNewsDto news : content) {
            if (news.isTitleEmpty()) {
                news.titleDefault(keyword);
            }
            news.validateFields();
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseNewsDtoPage);
    }

    @GetMapping("")
    public ResponseEntity<?> getNews(@RequestParam RequestNewsDto requestNewsDto) {

        if (requestNewsDto.getCompany().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("값을 입력해주세요.");
        }
        if (!(requestNewsDto.getCompany().isCompany())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("없는 값 입니다. 다시 입력해주세요.");
        }

        if (!requestNewsDto.getKeyword().isOver()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("검색어가 너무 깁니다. 다시 입력해주세요.");
        }
        ResponseNewsDto news = newsService.getNews(requestNewsDto);

        return ResponseEntity.status(HttpStatus.OK).body(news);
    }
}
