package org.likelion.newsfactbackend.domain.scraps.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestSaveScrapsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.response.ResponseScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.service.ScrapsService;
import org.likelion.newsfactbackend.global.domain.CommonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapsServiceImpl implements ScrapsService {

    @Override
    public ResponseScrapsNewsDto getScraps(RequestScrapsNewsDto requestScrapsNewsDto) {
        return null;
    }

    @Override
    public Boolean findScrapsById(Long id) {
        return null;
    }

    @Override
    public Boolean scrapNews(RequestSaveScrapsDto requestSaveScrapsDto) {
        return null;
    }

    @Override
    public Boolean deleteScrap(Long id) {
        return null;
    }

    public Page<ResponseScrapsNewsDto> getScrapsByPage(Long id, Pageable pageable){
        return null;
    }
}
