package com.llucasreis.reviewdgs.domain.externals;

import java.util.List;

import com.llucasreis.reviewdgs.domain.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
  private Long id;
  private List<Review> reviews;
}
