package org.likelion.newsfactbackend.domain.news.service;

import org.likelion.newsfactbackend.domain.news.dto.request.PageRequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {
    ResponseNewsDto getNews(RequestNewsDto chosunNewsRequestDto);
     Page<ResponseNewsDto> searchNews(PageRequestNewsDto pageRequestNewsDto);

}
