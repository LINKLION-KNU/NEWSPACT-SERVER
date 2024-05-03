package org.likelion.newsfactbackend.scraps.service;

import org.likelion.newsfactbackend.scraps.dto.request.ScrapsRequestDto;
import org.likelion.newsfactbackend.scraps.dto.response.ScrapsResponseDto;

public interface ScrapsService {
    ScrapsResponseDto getScraps(ScrapsRequestDto scrapsRequestDto);
}



