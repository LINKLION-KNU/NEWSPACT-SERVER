package org.likelion.newsfactbackend.domain.scraps.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.likelion.newsfactbackend.domain.news.service.NewsService;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestSaveScrapsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.response.ResponseScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.service.ScrapsService;
import org.likelion.newsfactbackend.global.security.JwtTokenProvider;
import org.likelion.newsfactbackend.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/scraps")
@RequiredArgsConstructor
public class ScrapsController {

    private final NewsService newsService;
    private final ScrapsService scrapsService;

    @GetMapping("/news")
    public ResponseEntity<?> getScraps(@RequestParam Long id,
        @RequestParam RequestScrapsNewsDto requestScrapsNewsDto){

        Integer page = requestScrapsNewsDto.getPage();
        Integer size = requestScrapsNewsDto.getSize();

        //1. 필드가 null인지 확인
        if (page == 0 || size == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("page값과 size값이 필요합니다.");
        }

        //2. 필드의 범위 확인
        if (page < 0 || size <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("페이지는 0 이상이어야 하며, 크기는 0보다 커야 합니다.");
        }

        Page<ResponseScrapsNewsDto> scraps = scrapsService.getScrapsByPage(id, PageRequest.of(page, size));


        return ResponseEntity.status(HttpStatus.OK).body(scraps);
    }


    @PostMapping()
    public ResponseEntity<?> createScrap(RequestSaveScrapsDto requestSaveScrapsDto, HttpServletRequest request, String name) {

        /*        *//* 1. 토큰 만료 예외 처리 *//*
        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 유효 기간 만료");
        }

        *//* 2. 사용자가 없을 경우 예외 처리 *//*
        if (userService.loadUserByUsername(name) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자를 찾을 수 없습니다.");
        }
        if (user.isEnabled()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("탈퇴 회원입니다.");
        }
        */

        if (requestSaveScrapsDto.getCompany().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company 값이 없습니다.");
        }
        if (requestSaveScrapsDto.getTitle().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("제목이 없습니다.");
        }
        if (requestSaveScrapsDto.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("글이 없습니다.");
        }
        if (requestSaveScrapsDto.getKeyword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("키워드 값이 없습니다.");
        }

        try {
            Boolean isSave = scrapsService.scrapNews(requestSaveScrapsDto);

            if (isSave) {
                return ResponseEntity.status(HttpStatus.OK).body("뉴스를 성공적으로 저장하였습니다.");
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알 수 없는 문제가 발생하였습니다.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("뉴스를 저장하는 중에 오류가 발생했습니다.");
        }
    }


    @DeleteMapping
    public ResponseEntity<?> deleteScrap(@RequestParam Long id) {

        //id가 null값이면 예외처리
        if (id == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력 값이 잘못되었습니다.");

        //id가 Scrpas에 존재하지 않으면 예외처리
        if (!scrapsService.findScrapsById(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 뉴스입니다.");

        //예외처리에 걸리지 않을 시 Delete
        try {
            if(scrapsService.deleteScrap(id)){
                return ResponseEntity.status(HttpStatus.OK).body("뉴스를 성공적으로 삭제하였습니다.");
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("알 수 없는 문제가 발생하였습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("뉴스를 삭제하는 중에 오류가 발생했습니다.");
        }
    }
}
