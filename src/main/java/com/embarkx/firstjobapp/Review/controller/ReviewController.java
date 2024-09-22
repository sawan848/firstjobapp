package com.embarkx.firstjobapp.Review.controller;

import com.embarkx.firstjobapp.Review.service.ReviewService;
import com.embarkx.firstjobapp.dto.ReviewRequest;
import com.embarkx.firstjobapp.dto.ReviewResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Validated
public class ReviewController {
    private final ReviewService service;


    @PostMapping("/save")
    public ResponseEntity<ReviewResponse> createReview(@RequestBody @Valid ReviewRequest review) {
        return new ResponseEntity<>(service.createReview( review), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponse>> getAllReview( ) {
        return ResponseEntity.of(Optional.ofNullable(service.findAllReview( )));
    }



    @PutMapping("/{reviewID}")
    public ResponseEntity<ReviewResponse> updateReview( @PathVariable
                                                 @NotNull(message = "Review ID cannot be empty")
                                                     String reviewID  ,
                                                     @RequestBody  @Valid ReviewRequest  request) {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(service.updateReview( reviewID  ,request));
    }
    @GetMapping("/{reviewID}")
    public ResponseEntity<ReviewResponse>getReviewByID(@PathVariable
                                                 @NotNull(message = "Review ID cannot be empty")

                                                     String reviewID  ) {
        return ResponseEntity.ok(service.findCompanyById(reviewID  ));
    }
    @DeleteMapping("/{reviewID}")
    public ResponseEntity<?> deleteReview( @PathVariable
                                                 @NotNull(message = "Review ID cannot be empty")

                                           String reviewID  ) {
        var deleted=service.deleteReviewById(reviewID  );
        if (deleted){
            return new ResponseEntity<>("Review deleted",HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
