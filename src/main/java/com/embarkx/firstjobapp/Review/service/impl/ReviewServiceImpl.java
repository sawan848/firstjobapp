package com.embarkx.firstjobapp.Review.service.impl;

import com.embarkx.firstjobapp.Review.model.Review;
import com.embarkx.firstjobapp.Review.repository.ReviewRepository;
import com.embarkx.firstjobapp.Review.service.ReviewService;
import com.embarkx.firstjobapp.dto.ReviewRequest;
import com.embarkx.firstjobapp.dto.ReviewResponse;
import com.embarkx.firstjobapp.exception.CompanyNotFoundException;
import com.embarkx.firstjobapp.exception.ReviewNotFoundException;
import com.embarkx.firstjobapp.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.embarkx.firstjobapp.mapper.ReviewReuestMappper.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;
    @Override
    public List<ReviewResponse> findAllReview() {
        return ReviewListToReviewResponseList(repository.findAll());

    }

    @Override
    public ReviewResponse  createReview(ReviewRequest  review) {
      try{
          var format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
          String created=format.format(ZonedDateTime.now());
          var request=CompanyRequestToReview(review,new Review());
          request.setId(UUID.randomUUID().toString());
          request.setCreatedAt(created);
          request.setUpdatedAt(created);
          log.info("created {}",request);
//          System.out.println("save = " + save);
          var save=repository.save(request);
         return ReviewToReviewResponse(save);


      }catch (ReviewNotFoundException e){
          throw new ReviewNotFoundException("Job not created ");
      }

    }

    @Override
    public ReviewResponse  updateReview(String reviewID, ReviewRequest review) {
        try {
            var format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
            String updated=format.format(ZonedDateTime.now());

            var response=findCompanyById(reviewID);
            if (response!=null){
              ReviewResponse  updateResponse =  response.update(review.title(),
                        review.description(),
                        review.rating(),
                        updated);
              var OriginalResponse=repository.save(
                      ReviewResponseToReview(updateResponse));
                log.info("created {}",response);

                return ReviewToReviewResponse(OriginalResponse);
//                repository.save(response);

            }
            throw new ReviewNotFoundException("Review is not updated with id "+reviewID);
        }catch (ReviewNotFoundException e){
            throw new ReviewNotFoundException("Review is not updated with id "+reviewID);

        }


    }

    @Override
    public ReviewResponse  findCompanyById(String reviewID) {
        Review job = repository.
                findById(reviewID).
                orElseThrow(() -> new ReviewNotFoundException("Review is not found with id" + reviewID));

        return  ReviewToReviewResponse(job);
    }

    @Override
    public boolean deleteReviewById(String reviewID) {
        Iterator<ReviewResponse > iterator=findAllReview().iterator();
        while (iterator.hasNext()){
            ReviewResponse  response=iterator.next();
            if (response.id().equals(reviewID)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }


}
