package com.llucasreis.reviewdgs.datafetchers;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.llucasreis.reviewdgs.dataloaders.ReviewDataLoader;
import com.llucasreis.reviewdgs.domain.entities.Game;
import com.llucasreis.reviewdgs.dtos.ReviewDTO;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;

import org.dataloader.DataLoader;

@DgsComponent
public class ReviewDataFetcher {

  @DgsEntityFetcher(name = "Game")
  public Game game(Map<String, Object> values) {
    return Game.builder().id((Long) values.get("id")).build();
  }

  @DgsData(parentType = "Game", field = "reviews")
  public CompletableFuture<ReviewDTO> review(DgsDataFetchingEnvironment dfe) {
    DataLoader<Long, ReviewDTO> reviewDataLoader = dfe.getDataLoader(ReviewDataLoader.class);

    Game game = dfe.getSource();

    return reviewDataLoader.load(game.getId());
  }

  
}
