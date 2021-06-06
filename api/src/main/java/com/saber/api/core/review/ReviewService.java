package com.saber.api.core.review;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {

    @GetMapping(value = "/review",produces = MediaType.APPLICATION_JSON_VALUE)
    List<Review> getReview(@RequestParam(value = "productId")int productId);
}