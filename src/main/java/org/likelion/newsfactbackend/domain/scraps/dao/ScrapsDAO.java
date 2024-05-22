package org.likelion.newsfactbackend.domain.scraps.dao;

import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestSaveScrapsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.response.ResponseScrapsNewsDto;
import org.springframework.http.ResponseEntity;

public interface ScrapsDAO {
    ResponseEntity<?> saveNews(RequestSaveScrapsDto requestSaveScrapsDto);
    ResponseScrapsNewsDto findScrapsNews(RequestScrapsNewsDto requestScrapsNewsDto);
}
