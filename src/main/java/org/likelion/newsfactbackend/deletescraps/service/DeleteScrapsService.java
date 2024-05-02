package org.likelion.newsfactbackend.deletescraps.service;

import org.likelion.newsfactbackend.deletescraps.dto.request.DeleteScrapsRequestDto;
import org.likelion.newsfactbackend.deletescraps.dto.response.DeleteScrapsResponseDto;

public interface DeleteScrapsService {
    DeleteScrapsResponseDto getDeleteScraps(DeleteScrapsRequestDto deleteScrapsRequestDto);
}
