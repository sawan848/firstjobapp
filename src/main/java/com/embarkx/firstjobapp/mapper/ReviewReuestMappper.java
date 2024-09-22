package com.embarkx.firstjobapp.mapper;

import com.embarkx.firstjobapp.Review.model.Review;
import com.embarkx.firstjobapp.dto.ReviewRequest;
import com.embarkx.firstjobapp.dto.ReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewReuestMappper {

    public static List<ReviewResponse>ReviewListToReviewResponseList(List<Review>reviewList){
        return reviewList.stream().map(review -> new ReviewResponse(
               review.getId(),
               review.getTitle(),
               review.getDescription(),
               review.getRating(),
               review.getCreatedAt(),
               review.getUpdatedAt()
        )).collect(Collectors.toList());



    }



    public static Review ReviewResponseToReview(ReviewResponse review){
        return new  Review(
               review.id(),
               review.title(),
               review.description(),
               review.rating(),
               review.createdAt(),
               review.updatedAt()
        );
    }
    public static ReviewResponse ReviewToReviewResponse(Review review){
        return new ReviewResponse(review.getId(),
               review.getTitle(),
               review.getDescription(),
               review.getRating(),
               review.getCreatedAt(),
               review.getUpdatedAt()
        );
    }

    public static Review CompanyRequestToReview(ReviewRequest request, Review review){
       review.setTitle(request.title());
       review.setDescription(request.description());
       review.setRating(request.rating());
        return review;
    }
}
