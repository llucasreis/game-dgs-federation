package com.llucasreis.gamedgs.datafetchers;

import java.util.List;
import java.util.Map;

import com.llucasreis.gamedgs.domain.entities.Game;
import com.llucasreis.gamedgs.repositories.GameRepository;
import com.netflix.graphql.dgs.*;

import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class GameDataFetcher {

  private final GameRepository gameRepository;

  @Autowired
  public GameDataFetcher(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @DgsQuery
  public List<Game> games(@InputArgument String name) {
    if (name == null) {
      return this.gameRepository.findAll();
    }
    return this.gameRepository.findByNameIsContainingIgnoreCase(name);
  }

  @DgsEntityFetcher(name = "Game")
  public Game game(Map<String, Object> values) {
    Long id = Long.parseLong((String) values.get("id"));

    return this.gameRepository.findById(id).orElse(null);
  }


}
