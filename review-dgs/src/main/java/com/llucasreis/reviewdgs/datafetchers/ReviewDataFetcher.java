package com.llucasreis.reviewdgs.datafetchers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.llucasreis.reviewdgs.dataloaders.ReviewByUserDataLoader;
import com.llucasreis.reviewdgs.dataloaders.ReviewByGameDataLoader;
import com.llucasreis.reviewdgs.domain.externals.Game;
import com.llucasreis.reviewdgs.domain.entities.Review;
import com.llucasreis.reviewdgs.domain.externals.User;
import com.llucasreis.reviewdgs.services.ReviewService;
import com.netflix.graphql.dgs.*;

import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class ReviewDataFetcher {

  private final ReviewService reviewService;

  @Autowired
  public ReviewDataFetcher(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @DgsEntityFetcher(name = "Game")
  public Game game(Map<String, Object> values) {
    Long id = Long.parseLong((String) values.get("id"));

    return Game.builder().id(id).build();
  }

  @DgsEntityFetcher(name = "User")
  public User user(Map<String, Object> values) {
    Long id = Long.parseLong((String) values.get("id"));

    return User.builder().id(id).build();
  }

  @DgsQuery
  public List<Review> reviews() {
    return this.reviewService.find();
  }

  @DgsData(parentType = "Review", field = "game")
  public Game game(DgsDataFetchingEnvironment dfe) {
    Review review = dfe.getSource();
    return Game.builder().id(review.getGameId()).build();
  }

  @DgsData(parentType = "Review", field = "user")
  public User user(DgsDataFetchingEnvironment dfe) {
    Review review = dfe.getSource();
    return User.builder().id(review.getUserId()).build();
  }

  @DgsData(parentType = "Game", field = "reviews")
  public CompletableFuture<Review> reviewByGame(DgsDataFetchingEnvironment dfe) {
    DataLoader<Long, Review> reviewByGameDataLoader = dfe.getDataLoader(ReviewByGameDataLoader.class);

    Game game = dfe.getSource();

    return reviewByGameDataLoader.load(game.getId());
  }

  @DgsData(parentType = "User", field = "reviews")
  public CompletableFuture<Review> reviewByUser(DgsDataFetchingEnvironment dfe) {
    DataLoader<Long, Review> reviewByUserDataLoader = dfe.getDataLoader(ReviewByUserDataLoader.class);

    User user = dfe.getSource();

    return reviewByUserDataLoader.load(user.getId());
  }

  
}
