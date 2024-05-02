package org.likelion.newsfactbackend.news.mt.service;

import org.likelion.newsfactbackend.news.mt.dto.request.MtNewsRequestDto;
import org.likelion.newsfactbackend.news.mt.dto.response.MtNewsResponseDto;

public interface MtNewsService {
    MtNewsResponseDto getMtNews(MtNewsRequestDto mtNewsRequestDto);
}
