package org.likelion.newsfactbackend.domain.news.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.newsfactbackend.domain.auth.dto.request.SignInRequestDto;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    @Autowired
    NewsService newsService;



    @GetMapping("")
    public ResponseEntity<?> sighIn(RequestNewsDto requestNewsDto){

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
        //객체 지향
        //DDD -> 객체지향을 극한으로 사용해서, 깔끔한 코드를 만든다
        if (!requestNewsDto.getKeyword().isOver()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("검색어가 너무 깁니다. 다시 입력해주세요.");
        }
        newsService.getNews(requestNewsDto);

        return null;
    }
}
