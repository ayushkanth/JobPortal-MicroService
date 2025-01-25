package com.learn.jobms.dto;

import java.util.List;

import com.learn.jobms.entity.Company;
import com.learn.jobms.entity.Review;

import lombok.Data;

@Data
public class JobDTO {
	private Long id;
	private String title;
	private String description;
	private String minSalary;
	private String maxSalary;
	private String location;
	private Company company;
	private List<Review> review;
}
