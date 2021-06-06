package com.saber.api.composite.product;

import java.util.Objects;

public class RecommendationSummary {
    private final int recommendationId;
    private final String author;
    private final int rate;

    public RecommendationSummary(int recommendationId, String author, int rate) {
        this.recommendationId = recommendationId;
        this.author = author;
        this.rate = rate;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public String getAuthor() {
        return author;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationSummary that = (RecommendationSummary) o;
        return recommendationId == that.recommendationId &&
                rate == that.rate &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recommendationId, author, rate);
    }

    @Override
    public String toString() {
        return "RecommendationSummary{" +
                "recommendationId=" + recommendationId +
                ", author='" + author + '\'' +
                ", rate=" + rate +
                '}';
    }
}
