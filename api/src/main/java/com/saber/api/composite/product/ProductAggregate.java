package com.saber.api.composite.product;

import java.util.List;
import java.util.Objects;

public class ProductAggregate {
    private final int productId;
    private final String name;
    private final int weight;
    private final List<RecommendationSummary> recommendations;
    private final List<ReviewSummary> reviews;
    private final ServiceAddresses serviceAddresses;

    public ProductAggregate(int productId, String name, int weight, List<RecommendationSummary> recommendations, List<ReviewSummary> reviews, ServiceAddresses serviceAddresses) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
        this.recommendations = recommendations;
        this.reviews = reviews;
        this.serviceAddresses = serviceAddresses;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public List<RecommendationSummary> getRecommendations() {
        return recommendations;
    }

    public List<ReviewSummary> getReviews() {
        return reviews;
    }

    public ServiceAddresses getServiceAddresses() {
        return serviceAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAggregate that = (ProductAggregate) o;
        return productId == that.productId &&
                weight == that.weight &&
                Objects.equals(name, that.name) &&
                Objects.equals(recommendations, that.recommendations) &&
                Objects.equals(reviews, that.reviews) &&
                Objects.equals(serviceAddresses, that.serviceAddresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, weight, recommendations, reviews, serviceAddresses);
    }

    @Override
    public String toString() {
        return "ProductAggregate{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", recommendations=" + recommendations +
                ", reviews=" + reviews +
                ", serviceAddresses=" + serviceAddresses +
                '}';
    }
}
