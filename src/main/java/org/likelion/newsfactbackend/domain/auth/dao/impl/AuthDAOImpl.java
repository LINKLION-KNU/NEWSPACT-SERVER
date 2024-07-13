package org.likelion.newsfactbackend.domain.auth.dao.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.auth.dao.AuthDAO;
import org.likelion.newsfactbackend.domain.auth.dao.NicknameDAO;
import org.likelion.newsfactbackend.domain.auth.dto.request.RequestSaveUserDto;
import org.likelion.newsfactbackend.domain.auth.dto.response.ResponseAuthDto;
import org.likelion.newsfactbackend.global.domain.CommonResponse;
import org.likelion.newsfactbackend.global.domain.enums.ResultCode;
import org.likelion.newsfactbackend.global.security.JwtTokenProvider;
import org.likelion.newsfactbackend.user.domain.User;
import org.likelion.newsfactbackend.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthDAOImpl implements AuthDAO {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final NicknameDAO nicknameDAO;

    @Override
    public ResponseEntity<?> login(RequestSaveUserDto requestSaveUserDto) {
        if (checkUserExist(requestSaveUserDto.getEmail(), requestSaveUserDto.getLoginType())) {
            User user = userRepository.findByEmailAndLoginType(requestSaveUserDto.getEmail(), requestSaveUserDto.getLoginType());
            if (user.isEnabled()) {
                return ResponseEntity.status(ResultCode.OK.getCode())
                        .body(ResponseAuthDto.builder()
                                .accessToken(jwtTokenProvider.createAccessToken(user.getEmail(), user.getNickName(), user.getRoles()))
                                .refreshToken(jwtTokenProvider.createRefreshToken(user.getEmail(), user.getNickName()))
                                .name(user.getUsername())
                                .status(CommonResponse.success())
                                .build());

            } else {
                return ResponseEntity.status(ResultCode.DELETED_USER.getCode())
                        .body(ResultCode.DELETED_USER);
            }
        } else {
            log.info("[sign up] no user");
            log.info("[auth] sign up");
            User user = User.builder()
                    .name(requestSaveUserDto.getName())
                    .nickName(nicknameDAO.generateNickName())
                    .password(requestSaveUserDto.getPassword())
                    .email(requestSaveUserDto.getEmail())
                    .profileUrl(requestSaveUserDto.getProfileUrl())
                    .loginType(requestSaveUserDto.getLoginType())
                    .useAble(requestSaveUserDto.isUseAble())
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
            int retryCount = 0;
            final int maxRetries = 5; // 재시도 횟수 제한
            while(retryCount < maxRetries){
                try {
                    userRepository.save(user);
                    log.info("[auth] signup success");

                    return ResponseEntity.status(ResultCode.OK.getCode())
                            .body(ResponseAuthDto.builder()
                                    .accessToken(jwtTokenProvider.createAccessToken(user.getEmail(), user.getNickName(), user.getRoles()))
                                    .refreshToken(jwtTokenProvider.createRefreshToken(user.getEmail(), user.getNickName()))
                                    .name(requestSaveUserDto.getName())
                                    .status(CommonResponse.success())
                                    .build());
                } catch (DataIntegrityViolationException e) { // 중복 시 재설정 후 다시 save
                    log.warn("[auth] signup failed due to duplicate nickname, retrying...");
                    user.setNickName(nicknameDAO.generateNickName());
                    retryCount++;
                }
            }
            log.error("[auth] signup failed due to max retries");
            return ResponseEntity.status(ResultCode.DUPLICATION_NICKNAME.getCode()).body(ResultCode.DUPLICATION_NICKNAME.getMessage());
        }
    }
    private boolean checkUserExist(String nickname, String loginType){
        return userRepository.existsByNickNameAndLoginType(nickname, loginType);
    }
}
