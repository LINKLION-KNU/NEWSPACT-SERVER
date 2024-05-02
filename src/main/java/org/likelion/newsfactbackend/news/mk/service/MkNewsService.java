package org.likelion.newsfactbackend.news.mk.service;

import org.likelion.newsfactbackend.news.mk.dto.request.MkNewsRequestDto;
import org.likelion.newsfactbackend.news.mk.dto.response.MkNewsResponseDto;

public interface MkNewsService {

    MkNewsResponseDto getNews(MkNewsRequestDto mkNewsRequestDto);
}
