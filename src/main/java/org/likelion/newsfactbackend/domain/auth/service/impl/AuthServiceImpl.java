package org.likelion.newsfactbackend.domain.auth.service.impl;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.auth.dao.AuthDAO;
import org.likelion.newsfactbackend.domain.auth.dto.request.RequestSignUpDto;
import org.likelion.newsfactbackend.domain.auth.service.AuthService;

import org.likelion.newsfactbackend.global.domain.CommonResponse;
import org.likelion.newsfactbackend.global.domain.enums.LoginType;
import org.likelion.newsfactbackend.global.domain.enums.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthDAO authDAO;

    private final String kakaoAccessTokenUrl = "https://kauth.kakao.com/oauth/token";
    private final String kakaoUserInfoUrl = "https://kapi.kakao.com/v2/user/me";

    @Value("${kakao.client.id}")
    private String clientKey;
    @Value("${kakao.redirect.url}")
    private String redirectUrl;

    @Override
    public ResponseEntity<?> kakaoLogin(String authorizeCode) {
        log.info("[kakao login] issue a authorizecode");
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientKey);
        params.add("redirect_uri", redirectUrl);
        params.add("code", authorizeCode);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    kakaoAccessTokenUrl,
                    HttpMethod.POST,
                    kakaoTokenRequest,
                    String.class
            );
            log.info("[kakao login] authorizecode issued successfully");
            Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
            String accessToken = (String) responseMap.get("access_token");

            RequestSignUpDto requestSignUpDto = getKakaoUserInfo(accessToken);

            return authDAO.signIn(requestSignUpDto);

        }catch (Exception e){
            log.warn("[kakao login] fail authorizecode issued");
            return ResponseEntity.status(ResultCode.PASSWORD_NOT_MATCH.getCode())
                    .body(CommonResponse.fail(ResultCode.PASSWORD_NOT_MATCH));
        }
    }

    private RequestSignUpDto getKakaoUserInfo(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("secure_resource", "true");

        HttpEntity<?> entity = new HttpEntity<>(requestBody,headers);

        ResponseEntity<String> response = restTemplate.postForEntity(kakaoUserInfoUrl,entity,String.class);

        try{
            Map<String, Object> responseMap = mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
            Map<String, Object> kakaoAccount = (Map<String, Object>) responseMap.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            RequestSignUpDto requestSignUpDto = RequestSignUpDto.builder()
                    .userName((String) kakaoAccount.get("name"))
                    .nickName((String) kakaoAccount.get("nickname"))
                    .password(getRandomPassword())
                    .phoneNumber((String) kakaoAccount.get("phone_number"))
                    .email((String)kakaoAccount.get("email"))
                    .profileUrl((String) profile.get("profile_image_url"))
                    .loginType(LoginType.KAKAO.toString())
                    .useAble(true)
                    .build();

            return requestSignUpDto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    private String getRandomPassword() {
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }
}
