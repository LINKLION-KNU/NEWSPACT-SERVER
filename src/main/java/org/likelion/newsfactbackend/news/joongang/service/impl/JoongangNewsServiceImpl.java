package org.likelion.newsfactbackend.news.joongang.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.joongang.dto.request.JoongangNewsRequestDto;
import org.likelion.newsfactbackend.news.joongang.dto.response.JoongangNewsResponseDto;
import org.likelion.newsfactbackend.news.joongang.service.JoongangNewsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoongangNewsServiceImpl implements JoongangNewsService {
    @Override
    public JoongangNewsResponseDto getJoongangNews(JoongangNewsRequestDto joongangNewsRequestDto) {
        return null;
    }
}
