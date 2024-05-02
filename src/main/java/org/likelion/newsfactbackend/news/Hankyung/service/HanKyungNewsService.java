package org.likelion.newsfactbackend.news.Hankyung.service;

import org.likelion.newsfactbackend.news.Hankyung.dto.request.HangkyungNewsRequestDto;
import org.likelion.newsfactbackend.news.Hankyung.dto.response.HankyungNewsResponseDto;

public interface HanKyungNewsService {
    HankyungNewsResponseDto getHankyungNews(HangkyungNewsRequestDto hangkyungNewsRequestDto);
}
