package org.likelion.newsfactbackend.domain.scraps.service;

import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.response.ResponseScrapsNewsDto;
import org.springframework.http.ResponseEntity;

public interface ScrapsService {
     ResponseScrapsNewsDto getScraps(RequestScrapsNewsDto requestScrapsNewsDto);
     ResponseEntity<?> scrapNews(RequestScrapsNewsDto requestScrapsNewsDto);
}



