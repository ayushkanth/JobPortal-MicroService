package com.learn.reviewms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.reviewms.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{
	List<Review> findByCompanyId(Long companyId);
}
