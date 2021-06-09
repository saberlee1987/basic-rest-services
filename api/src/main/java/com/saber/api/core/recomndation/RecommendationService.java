package com.saber.api.core.recomndation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
public interface RecommendationService {

    @GetMapping(value = "/recommendation",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RecommendationResponse> getRecommendations(@RequestParam(value = "productId")int productId);
}
