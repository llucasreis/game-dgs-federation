package com.llucasreis.reviewdgs.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.llucasreis.reviewdgs.domain.entities.Review;
import com.llucasreis.reviewdgs.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

  private final ReviewRepository reviewRepository;

  @Autowired
  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public List<Review> find() {
    return this.reviewRepository.findAll();
  }

  public Map<Long, List<Review>> getReviewsByGameIds(List<Long> gameIds) {
    List<Review> reviews = this.reviewRepository.findByGameIdIn(gameIds);
    Map<Long, List<Review>> response = new HashMap<>();

    gameIds.forEach(gameId -> {
      List<Review> gameReviews = reviews.stream()
                  .filter(r -> r.getGameId().equals(gameId))
                  .collect(Collectors.toList());
      response.put(gameId, gameReviews);
    });

    return response;
  }
  
}
