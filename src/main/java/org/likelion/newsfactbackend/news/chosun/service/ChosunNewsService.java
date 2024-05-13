package org.likelion.newsfactbackend.news.chosun.service;

import jakarta.servlet.http.HttpServletRequest;
import org.likelion.newsfactbackend.news.chosun.dto.request.ChosunNewsRequestDto;
import org.likelion.newsfactbackend.news.chosun.dto.response.ChosunNewsResponseDto;

public interface ChosunNewsService {
    ChosunNewsResponseDto getChosunNews(ChosunNewsRequestDto chosunNewsRequestDto);
}
