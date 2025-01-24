package com.learn.reviewms.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.learn.reviewms.entity.Review;
import com.learn.reviewms.repo.ReviewRepo;
import com.learn.reviewms.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

	private final ReviewRepo reviewRepo;
	
	
	@Override
	public List<Review> findAllReviews(Long companyId) {
			List<Review> reviews = reviewRepo.findByCompanyId(companyId);
			return reviews;
	}

	@Override
	public boolean createReview(Long companyId,Review review) {
		if(companyId!=null && review!=null)
		{
			review.setCompanyId(companyId);
			reviewRepo.save(review);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Review getReviewById(Long reviewId) {
		return reviewRepo.findById(reviewId).orElse(null);
	}

	@Override
	public boolean updateReviewById( Long reviewId, Review updatedReview) {
		Review review = reviewRepo.findById(reviewId).orElse(null);
		if(review!=null)
		{
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setCompanyId(updatedReview.getCompanyId());
			review.setRating(updatedReview.getRating());
			reviewRepo.save(review);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteReviewById(Long reviewId) {
		Review review = reviewRepo.findById(reviewId).orElse(null);
		if(review!=null)
		{
			reviewRepo.delete(review);
			return true;
		}
		return false;
	}



}
