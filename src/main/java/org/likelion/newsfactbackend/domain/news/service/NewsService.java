package org.likelion.newsfactbackend.domain.news.service;

import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {
    ResponseNewsDto getNews(RequestNewsDto chosunNewsRequestDto);
}
