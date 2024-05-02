package org.likelion.newsfactbackend.news.mt.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.mt.dto.request.MtNewsRequestDto;
import org.likelion.newsfactbackend.news.mt.dto.response.MtNewsResponseDto;
import org.likelion.newsfactbackend.news.mt.service.MtNewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MtNewsServiceImpl implements MtNewsService {
    @Override
    public MtNewsResponseDto getMtNews(MtNewsRequestDto mtNewsRequestDto) {
        return null;
    }
}

