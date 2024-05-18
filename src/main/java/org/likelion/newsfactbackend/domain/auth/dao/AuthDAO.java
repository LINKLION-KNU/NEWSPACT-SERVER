package org.likelion.newsfactbackend.domain.auth.dao;

import org.likelion.newsfactbackend.domain.auth.dto.request.RequestSignUpDto;
import org.springframework.http.ResponseEntity;


public interface AuthDAO {
    ResponseEntity<?> signIn(RequestSignUpDto requestSignUpDto);
}
