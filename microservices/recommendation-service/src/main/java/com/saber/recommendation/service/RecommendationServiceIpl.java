package com.saber.recommendation.service;

import com.saber.api.core.recomndation.Recommendation;
import com.saber.api.core.recomndation.RecommendationResponse;
import com.saber.api.core.recomndation.RecommendationService;
import com.saber.util.exceptions.InvalidInputException;
import com.saber.util.exceptions.NotFoundException;
import com.saber.util.http.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
public class RecommendationServiceIpl implements RecommendationService {

    private final ServiceUtil serviceUtil;

    public RecommendationServiceIpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }


    @Override
    public ResponseEntity<RecommendationResponse> getRecommendations(int productId) {

        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if (productId == 113) {
            log.debug("No recommendations found for productId: {}", productId);
            throw new NotFoundException("No recommendations found for productId:"+productId);
        }

        List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3", serviceUtil.getServiceAddress()));

        log.debug("/recommendation response size: {}", list.size());

        RecommendationResponse response = new RecommendationResponse();
        response.setRecommendations(list);
        return ResponseEntity.ok(response);
    }
}
