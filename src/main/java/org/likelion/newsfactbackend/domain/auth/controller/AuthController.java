package org.likelion.newsfactbackend.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.auth.service.AuthService;
import org.likelion.newsfactbackend.global.domain.enums.LoginType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    @Operation(summary = "구글 소셜 로그인 콜백 컨트롤러 입니다.")
    @GetMapping("/google/callback")
    public ResponseEntity<?> getGoogleAuthorizeCode(@RequestParam("code") String authorizeCode){
        String type = LoginType.GOOGLE.toString();
        log.info("[google login] issue authorizecode successful");
        log.info("[google login] authorizecode : {}",authorizeCode);
        return authService.signIn(authorizeCode, type);
    }
    @Operation(summary = "카카오 소셜 로그인 콜백 컨트롤러 입니다.")
    @GetMapping("/kakao/callback")
    public ResponseEntity<?> getKaKaoAuthorizeCode(@RequestParam("code") String authorizeCode){
        String type = LoginType.KAKAO.toString();
        log.info("[kakao login] issue authorizecode successful");
        return authService.signIn(authorizeCode, type);
    }
}
