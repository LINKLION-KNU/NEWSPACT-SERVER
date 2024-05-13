package org.likelion.newsfactbackend.domain.news.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.newsfactbackend.domain.auth.dto.request.SignInRequestDto;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
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

        newsService.getNews(requestNewsDto);

        return null;
    }
}
