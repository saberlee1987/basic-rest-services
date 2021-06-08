package com.saber.review.service;

import com.saber.api.core.review.Review;
import com.saber.api.core.review.ReviewService;
import com.saber.util.exceptions.InvalidInputException;
import com.saber.util.http.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public List<Review> getReview(int productId) {

        if (productId<1) throw new InvalidInputException("Invalid productId : "+productId);

        if (productId==123){
            log.debug("No reviews found for productId : {} ",productId);
            return new ArrayList<>();
        }
        List<Review> list = new ArrayList<>();
        list.add(new Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress()));

        log.debug("/reviews response size: {}", list.size());
        return list;
    }
}
