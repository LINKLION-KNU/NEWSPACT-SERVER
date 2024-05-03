package org.likelion.newsfactbackend.scraps.service;

import org.likelion.newsfactbackend.scraps.dto.request.ScrapsNewsRequestDto;
import org.likelion.newsfactbackend.scraps.dto.response.ScrapsNewsResponseDto;


public interface ScrapsNewsService {
    ScrapsNewsResponseDto getScrapsNews(ScrapsNewsRequestDto scrapsNewsRequestDto);
}


