package org.likelion.newsfactbackend.domain.scraps.service;

import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestSaveScrapsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.request.RequestScrapsNewsDto;
import org.likelion.newsfactbackend.domain.scraps.dto.response.ResponseScrapsNewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ScrapsService {
     ResponseScrapsNewsDto getScraps(RequestScrapsNewsDto requestScrapsNewsDto);
     //@Param: id -> scrap id
     //@Retrun: Boolean -> exist = true, not exist -> false
     Boolean findScrapsById(Long id);
     /*ResponseEntity<?> scrapNews(RequestScrapsNewsDto requestScrapsNewsDto);*/
//     ResponseScrapsNewsDto

    Boolean scrapNews(RequestSaveScrapsDto requestSaveScrapsDto);

    Boolean deleteScrap(Long id);
    //page service
    Page<ResponseScrapsNewsDto> getScrapsByPage(Long id, Pageable pageable);
}




