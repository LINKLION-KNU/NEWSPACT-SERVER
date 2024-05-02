package org.likelion.newsfactbackend.news.khan.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.khan.dto.request.KhanNewsRequestDto;
import org.likelion.newsfactbackend.news.khan.dto.response.KhanNewsResponseDto;
import org.likelion.newsfactbackend.news.khan.service.KhanNewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KhanNewsServiceImpl implements KhanNewsService {
    public KhanNewsResponseDto getKhanNews(KhanNewsRequestDto khanNewsRequestDto){
        return null;
    }
}
