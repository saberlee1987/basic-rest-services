package com.saber.api.core.review;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
public interface ReviewService {

    @GetMapping(value = "/review",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ReviewResponse> getReview(@RequestParam(value = "productId")int productId);
}
