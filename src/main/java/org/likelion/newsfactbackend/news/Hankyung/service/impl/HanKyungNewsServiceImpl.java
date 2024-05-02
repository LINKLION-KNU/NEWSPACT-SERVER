package org.likelion.newsfactbackend.news.Hankyung.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.news.Hankyung.dto.request.HangkyungNewsRequestDto;
import org.likelion.newsfactbackend.news.Hankyung.dto.response.HankyungNewsResponseDto;
import org.likelion.newsfactbackend.news.Hankyung.service.HanKyungNewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HanKyungNewsServiceImpl implements HanKyungNewsService {
    @Override
    public HankyungNewsResponseDto getHankyungNews(HangkyungNewsRequestDto hangkyungNewsRequestDto){
        return null;
    }

}
