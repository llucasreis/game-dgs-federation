package com.llucasreis.reviewdgs.repositories;

import java.util.List;

import com.llucasreis.reviewdgs.domain.entities.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findByGameIdIn(List<Long> gameIds);
  
}
