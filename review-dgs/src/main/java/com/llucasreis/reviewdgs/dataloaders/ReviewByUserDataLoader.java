package com.llucasreis.reviewdgs.dataloaders;

import com.llucasreis.reviewdgs.domain.entities.Review;
import com.llucasreis.reviewdgs.services.ReviewService;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.MappedBatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name = "userReviews")
public class ReviewByUserDataLoader implements MappedBatchLoader<Long, List<Review>> {

    private final ReviewService reviewService;

    @Autowired
    public ReviewByUserDataLoader(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public CompletionStage<Map<Long, List<Review>>> load(Set<Long> userIds) {
        return CompletableFuture.supplyAsync(() ->
                this.reviewService.getReviewsByUserIds(new ArrayList<>(userIds)));
    }
}
