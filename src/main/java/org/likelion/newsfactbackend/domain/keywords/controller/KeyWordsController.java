package org.likelion.newsfactbackend.domain.keywords.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/keywords")
public class KeyWordsController {
    @GetMapping()
    public ResponseEntity<?> getKeywords(){
        return null;
    }

}
