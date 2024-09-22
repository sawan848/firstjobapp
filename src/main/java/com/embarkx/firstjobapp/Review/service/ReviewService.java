package com.embarkx.firstjobapp.Review.service;

import com.embarkx.firstjobapp.dto.ReviewRequest;
import com.embarkx.firstjobapp.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
     List<ReviewResponse >findAllReview();
     ReviewResponse  createReview(ReviewRequest  review);
     ReviewResponse  updateReview(String reviewID,ReviewRequest  review);
     ReviewResponse  findCompanyById(String reviewID);
     boolean  deleteReviewById(String reviewID);

}
