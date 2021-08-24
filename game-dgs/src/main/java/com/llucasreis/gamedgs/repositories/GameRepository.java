package com.llucasreis.gamedgs.repositories;

import com.llucasreis.gamedgs.domain.entities.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByNameIsContainingIgnoreCase(String name);
}
