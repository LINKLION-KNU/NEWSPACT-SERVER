package org.likelion.newsfactbackend.domain.news.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.likelion.newsfactbackend.domain.news.repository.NewsRepository;
import org.likelion.newsfactbackend.domain.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;


    @GetMapping("")
    public ResponseEntity<?> getNews(@RequestParam RequestNewsDto requestNewsDto){

         /*
    1. 예외처리
     -> company 값이 null일 경우 예외처리 & company가 없을 경우 예외처리
     -> keyword(검색 값)이 255자를 넘을 경우 예외처리
    2. 예외가 아닐 경우 정상 코드
     */

        if (requestNewsDto.getCompany().isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("값을 입력해주세요.");
        }
        if (!(requestNewsDto.getCompany().isCompany())){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("없는 값 입니다. 다시 입력해주세요.");
        }

        if (!requestNewsDto.getKeyword().isOver()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("검색어가 너무 깁니다. 다시 입력해주세요.");
        }
        ResponseNewsDto news = newsService.getNews(requestNewsDto);

        return ResponseEntity.status(HttpStatus.OK).body(news);
    }
}
