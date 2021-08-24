package com.llucasreis.reviewdgs.domain.externals;

import com.llucasreis.reviewdgs.domain.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private List<Review> reviews;
}
