package com.learn.reviewms.service;

import java.util.List;

import com.learn.reviewms.entity.Review;

public interface ReviewService {

	List<Review> findAllReviews(Long companyId);
	
	boolean createReview(Long companyId,Review review);
	
	Review getReviewById(Long reviewId);
	
	boolean deleteReviewById(Long reviewId);
	
	boolean updateReviewById(Long reviewId,Review upReview);
}
