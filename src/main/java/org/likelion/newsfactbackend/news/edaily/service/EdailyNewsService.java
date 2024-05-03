package org.likelion.newsfactbackend.news.edaily.service;

import org.likelion.newsfactbackend.news.edaily.dto.request.EdailyNewsRequestDto;
import org.likelion.newsfactbackend.news.edaily.dto.response.EdailyNewsResponseDto;

public interface EdailyNewsService {
    EdailyNewsResponseDto getEdailyNews(EdailyNewsRequestDto edailyNewsRequestDto);
}
