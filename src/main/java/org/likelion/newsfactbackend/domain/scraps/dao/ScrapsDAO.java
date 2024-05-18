package org.likelion.newsfactbackend.domain.scraps.dao;

import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestSaveScrapsDto;
import org.springframework.http.ResponseEntity;

public interface ScrapsDAO {
    ResponseEntity<?> saveNews(RequestSaveScrapsDto requestSaveScrapsDto);
}
