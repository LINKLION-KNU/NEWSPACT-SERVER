package org.likelion.newsfactbackend.scraps.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.scraps.dto.request.ScrapsRequestDto;
import org.likelion.newsfactbackend.scraps.dto.response.ScrapsResponseDto;
import org.likelion.newsfactbackend.scraps.service.ScrapsService;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ScrapsServiceImpl implements ScrapsService {
    @Override
    public ScrapsResponseDto getScraps(ScrapsRequestDto scrapsRequestDto){
        return null;
    }
}
