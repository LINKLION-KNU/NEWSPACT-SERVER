package org.likelion.newsfactbackend.news.chosun.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.keyword.repository.TrendKeywordRepository;
import org.likelion.newsfactbackend.news.chosun.dto.request.ChosunNewsRequestDto;
import org.likelion.newsfactbackend.news.chosun.dto.response.ChosunNewsResponseDto;
import org.likelion.newsfactbackend.news.chosun.service.ChosunNewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chosun-news")
public class ChosunController {
    private final ChosunNewsService chosunNewsService;
    @GetMapping()
    public ResponseEntity<?> getChosunNews(@RequestParam String query){
        ChosunNewsRequestDto chosunNewsRequestDto = new ChosunNewsRequestDto();
        chosunNewsRequestDto.setSearch(query);

         return ResponseEntity.ok(chosunNewsService.getChosunNews(chosunNewsRequestDto));
    }
}
