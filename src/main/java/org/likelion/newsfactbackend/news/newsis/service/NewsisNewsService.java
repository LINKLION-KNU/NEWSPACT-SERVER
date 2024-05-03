package org.likelion.newsfactbackend.news.newsis.service;


import org.likelion.newsfactbackend.news.newsis.dto.request.NewsisNewsRequestDto;
import org.likelion.newsfactbackend.news.newsis.dto.response.NewsisNewsResponseDto;

public interface NewsisNewsService {
    NewsisNewsResponseDto getNewsis(NewsisNewsRequestDto newsisNewsRequestDto);
}
