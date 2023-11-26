package review.entity;

import jakarta.persistence.Column;

public class ReviewDto {

    private int rank;

    private String confidence;

    private String comment;

    public ReviewDto(int rank, String confidence, String comment) {
        this.rank = rank;
        this.confidence = confidence;
        this.comment = comment;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
