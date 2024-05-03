package org.likelion.newsfactbackend.news.yna.service;

import org.likelion.newsfactbackend.news.yna.dto.request.YnaNewsRequestDto;
import org.likelion.newsfactbackend.news.yna.dto.response.YnaNewsResponseDto;

public interface YnaNewsService {
    YnaNewsResponseDto getYnaNews (YnaNewsRequestDto ynaNewsRequestDto);

}
