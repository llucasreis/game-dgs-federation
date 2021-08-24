package com.llucasreis.reviewdgs.dataloaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.llucasreis.reviewdgs.domain.entities.Review;
import com.llucasreis.reviewdgs.services.ReviewService;
import com.netflix.graphql.dgs.DgsDataLoader;

import org.dataloader.MappedBatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

@DgsDataLoader(name = "gameReviews")
public class ReviewByGameDataLoader implements MappedBatchLoader<Long, List<Review>> {

  private final ReviewService reviewService;

  @Autowired
  public ReviewByGameDataLoader(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @Override
  public CompletionStage<Map<Long, List<Review>>> load(Set<Long> gameIds) {
    return CompletableFuture.supplyAsync(() -> 
              this.reviewService.getReviewsByGameIds(new ArrayList<>(gameIds)));
  }
  
}
