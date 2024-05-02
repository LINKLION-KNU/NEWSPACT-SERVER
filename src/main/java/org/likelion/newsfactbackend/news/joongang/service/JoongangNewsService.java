package org.likelion.newsfactbackend.news.joongang.service;

import org.likelion.newsfactbackend.news.joongang.dto.request.JoongangNewsRequestDto;
import org.likelion.newsfactbackend.news.joongang.dto.response.JoongangNewsResponseDto;

public interface JoongangNewsService {
    JoongangNewsResponseDto getJoongangNews(JoongangNewsRequestDto joongangNewsRequestDto);
}

