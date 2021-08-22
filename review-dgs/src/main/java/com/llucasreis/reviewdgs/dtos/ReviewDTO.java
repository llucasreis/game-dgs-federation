package com.llucasreis.reviewdgs.dtos;

import com.llucasreis.reviewdgs.domain.entities.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
  private Long id;
  private Integer starRating;
  private String comment;

  public static ReviewDTO convert(Review review) {
    return new ReviewDTO(review.getId(), review.getStarRating(), review.getComment());
  }
}
