package org.likelion.newsfactbackend.domain.auth.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> kakaoLogin(String authorizeCode);
}
