package org.likelion.newsfactbackend.news.chosun.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.chosun.dto.request.ChosunNewsRequestDto;
import org.likelion.newsfactbackend.news.chosun.dto.response.ChosunNewsResponseDto;
import org.likelion.newsfactbackend.news.chosun.service.ChosunNewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChosunNewsServiceImpl implements ChosunNewsService {
    @Override
    public ChosunNewsResponseDto getChosunNews(ChosunNewsRequestDto chosunNewsRequestDto) {
        return null;
    }
}
