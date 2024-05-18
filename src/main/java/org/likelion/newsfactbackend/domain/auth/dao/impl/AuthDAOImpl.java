package org.likelion.newsfactbackend.domain.auth.dao.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.auth.dao.AuthDAO;
import org.likelion.newsfactbackend.domain.auth.dto.request.RequestSignUpDto;
import org.likelion.newsfactbackend.domain.auth.dto.response.ResponseAuthDto;
import org.likelion.newsfactbackend.global.domain.CommonResponse;
import org.likelion.newsfactbackend.global.domain.enums.ResultCode;
import org.likelion.newsfactbackend.global.security.JwtTokenProvider;
import org.likelion.newsfactbackend.user.domain.User;
import org.likelion.newsfactbackend.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthDAOImpl implements AuthDAO {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> signIn(RequestSignUpDto requestSignUpDto) {

        if (checkUserExist(requestSignUpDto.getEmail(), requestSignUpDto.getLoginType())) { // email 이 존재?
            User user = userRepository.findByEmail(requestSignUpDto.getEmail());
            log.info("user name : {}",user.getUsername());
            if (user.isEnabled()) {
                return ResponseEntity.status(ResultCode.OK.getCode())
                        .body(ResponseAuthDto.builder()
                                .accessToken(jwtTokenProvider.createAccessToken(user.getEmail(), user.getRoles()))
                                .refreshToken(jwtTokenProvider.createRefreshToken(user.getEmail()))
                                .name(user.getUsername())
                                .status(CommonResponse.success())
                                .build());

            } else {
                return ResponseEntity.status(ResultCode.DELETED_USER.getCode())
                        .body(ResultCode.DELETED_USER);
            }
        } else {
            CommonResponse commonResponse = signUp(requestSignUpDto);
            if (commonResponse.getCode() == 200) {
                return signIn(requestSignUpDto);
            }
        }
        return null;
    }


    private CommonResponse signUp(RequestSignUpDto requestSignUpDto){
        User user = User.builder()
                .userName(requestSignUpDto.getUserName())
                .password(requestSignUpDto.getPassword())
                .phoneNumber(requestSignUpDto.getPhoneNumber())
                .email(requestSignUpDto.getEmail())
                .profileUrl(requestSignUpDto.getProfileUrl())
                .loginType(requestSignUpDto.getLoginType())
                .useAble(requestSignUpDto.getUseAble())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        userRepository.save(user);

        return CommonResponse.success();
    }
    private boolean checkUserExist(String email, String loginType){
        return userRepository.existsByEmailAndLoginType(email, loginType);
    }
}
