package com.saber.api.core.review;

import java.util.Objects;

public class Review {
    private final int productId;
    private final int reviewId;
    private final String author;
    private final String subject;
    private final String content;
    private final String serviceAddress;

    public Review(){
        this.productId=0;
        this.reviewId=0;
        this.author=null;
        this.subject=null;
        this.content=null;
        this.serviceAddress=null;
    }

    public Review(int productId, int reviewId, String author, String subject, String content, String serviceAddress) {
        this.productId = productId;
        this.reviewId = reviewId;
        this.author = author;
        this.subject = subject;
        this.content = content;
        this.serviceAddress = serviceAddress;
    }

    public int getProductId() {
        return productId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return productId == review.productId &&
                reviewId == review.reviewId &&
                Objects.equals(author, review.author) &&
                Objects.equals(subject, review.subject) &&
                Objects.equals(content, review.content) &&
                Objects.equals(serviceAddress, review.serviceAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, reviewId, author, subject, content, serviceAddress);
    }

    @Override
    public String toString() {
        return "Review{" +
                "productId=" + productId +
                ", reviewId=" + reviewId +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", serviceAddress='" + serviceAddress + '\'' +
                '}';
    }
}
