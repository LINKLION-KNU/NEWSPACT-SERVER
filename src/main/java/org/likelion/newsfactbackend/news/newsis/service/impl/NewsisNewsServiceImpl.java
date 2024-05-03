package org.likelion.newsfactbackend.news.newsis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.mt.dto.request.MtNewsRequestDto;
import org.likelion.newsfactbackend.news.mt.dto.response.MtNewsResponseDto;
import org.likelion.newsfactbackend.news.mt.service.MtNewsService;
import org.likelion.newsfactbackend.news.newsis.dto.request.NewsisNewsRequestDto;
import org.likelion.newsfactbackend.news.newsis.dto.response.NewsisNewsResponseDto;
import org.likelion.newsfactbackend.news.newsis.service.NewsisNewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NewsisNewsServiceImpl implements NewsisNewsService {
    @Override
    public NewsisNewsResponseDto getNewsis(NewsisNewsRequestDto newsisNewsRequestDto){
        return null;
    }
}


