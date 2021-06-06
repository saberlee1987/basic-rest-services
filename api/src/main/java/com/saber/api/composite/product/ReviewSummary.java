package com.saber.api.composite.product;

import java.util.Objects;

public class ReviewSummary {
    private final int productId;
    private final String author;
    private final String subject;

    public ReviewSummary(int productId, String author, String subject) {
        this.productId = productId;
        this.author = author;
        this.subject = subject;
    }

    public int getProductId() {
        return productId;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewSummary that = (ReviewSummary) o;
        return productId == that.productId &&
                Objects.equals(author, that.author) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, author, subject);
    }

    @Override
    public String toString() {
        return "ReviewSummary{" +
                "productId=" + productId +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
