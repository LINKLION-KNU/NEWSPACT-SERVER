package org.likelion.newsfactbackend.news.edaily.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.edaily.dto.request.EdailyNewsRequestDto;
import org.likelion.newsfactbackend.news.edaily.dto.response.EdailyNewsResponseDto;
import org.likelion.newsfactbackend.news.edaily.service.EdailyNewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EdailyNewsServiceImpl implements EdailyNewsService {
    @Override
    public EdailyNewsResponseDto getEdailyNews(EdailyNewsRequestDto edailyNewsRequestDto){
        return null;
    }
}
