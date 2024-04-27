package org.likelion.newsfactbackend.auth.service;


import org.likelion.newsfactbackend.auth.dto.request.SignInRequestDto;
import org.likelion.newsfactbackend.auth.dto.request.SignUpRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> signUp(SignUpRequestDto signUpRequestDto);
    ResponseEntity<?> signIn(SignInRequestDto signInRequestDto);
}
