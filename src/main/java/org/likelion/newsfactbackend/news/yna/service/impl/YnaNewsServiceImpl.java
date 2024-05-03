package org.likelion.newsfactbackend.news.yna.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.yna.dto.request.YnaNewsRequestDto;
import org.likelion.newsfactbackend.news.yna.dto.response.YnaNewsResponseDto;
import org.likelion.newsfactbackend.news.yna.service.YnaNewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class YnaNewsServiceImpl implements YnaNewsService {
    @Override
    public YnaNewsResponseDto getYnaNews(YnaNewsRequestDto ynaNewsRequestDto){
        return null;
    }
}
