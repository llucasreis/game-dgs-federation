package com.llucasreis.gamedgs.datafetchers;

import java.util.List;

import com.llucasreis.gamedgs.domain.entities.Game;
import com.llucasreis.gamedgs.repositories.GameRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class GameDataFetcher {

  private final GameRepository gameRepository;

  @Autowired
  public GameDataFetcher(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @DgsQuery
  public List<Game> games() {
    return this.gameRepository.findAll();
  }


}
