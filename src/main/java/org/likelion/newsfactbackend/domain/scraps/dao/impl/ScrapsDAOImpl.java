package org.likelion.newsfactbackend.domain.scraps.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.scraps.dao.ScrapsDAO;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestSaveScrapsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.response.ResponseScrapsNewsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScrapsDAOImpl implements ScrapsDAO {
    @Override
    public ResponseEntity<?> saveNews(RequestSaveScrapsDto requestSaveScrapsDto) {
        return null;
    }

    @Override
    public ResponseScrapsNewsDto findScrapsNews(RequestScrapsNewsDto requestScrapsNewsDto) {
        return null;
    }
}
