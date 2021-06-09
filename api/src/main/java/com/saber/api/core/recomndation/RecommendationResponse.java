package com.saber.api.core.recomndation;

import java.util.List;
import java.util.Objects;

public class RecommendationResponse {
    private List<Recommendation> recommendations;

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationResponse that = (RecommendationResponse) o;
        return Objects.equals(recommendations, that.recommendations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recommendations);
    }

    @Override
    public String toString() {
        return "RecommendationResponse{" +
                "recommendations=" + recommendations +
                '}';
    }
}
