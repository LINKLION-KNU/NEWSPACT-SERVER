package org.likelion.newsfactbackend.domain.auth.service;


import org.likelion.newsfactbackend.domain.auth.dto.request.SignInRequestDto;
import org.likelion.newsfactbackend.domain.auth.dto.request.SignUpRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> signUp(SignUpRequestDto signUpRequestDto);
    ResponseEntity<?> signIn(SignInRequestDto signInRequestDto);
}
