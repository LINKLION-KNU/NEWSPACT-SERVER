package org.likelion.newsfactbackend.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.newsfactbackend.domain.auth.dto.request.SignInRequestDto;
import org.likelion.newsfactbackend.domain.auth.dto.request.SignUpRequestDto;
import org.likelion.newsfactbackend.domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> sighUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return authService.signUp(signUpRequestDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> sighIn(@RequestBody SignInRequestDto signInRequestDto){
        return authService.signIn(signInRequestDto);
    }
}
