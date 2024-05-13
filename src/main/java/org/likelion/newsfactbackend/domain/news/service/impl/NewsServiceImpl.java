package org.likelion.newsfactbackend.domain.news.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.likelion.newsfactbackend.domain.news.service.NewsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NewsServiceImpl implements NewsService {
    @Override
    public ResponseNewsDto getNews(RequestNewsDto requestNewsDto) {
        return new ResponseNewsDto();
    }
}