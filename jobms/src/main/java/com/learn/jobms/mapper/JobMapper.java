package com.learn.jobms.mapper;

import java.util.List;

import com.learn.jobms.dto.JobDTO;
import com.learn.jobms.entity.Company;
import com.learn.jobms.entity.Job;
import com.learn.jobms.entity.Review;

public class JobMapper {

	public static JobDTO mapToJobDTO(Job job, Company company, List<Review> reviews)
	{
		JobDTO jobDTO = new JobDTO();
		jobDTO.setId(job.getId());
		jobDTO.setCompany(company);
		jobDTO.setDescription(job.getDescription());
		jobDTO.setLocation(job.getLocation());
		jobDTO.setMaxSalary(job.getMaxSalary());
		jobDTO.setMinSalary(job.getMinSalary());
		jobDTO.setTitle(job.getTitle());
		jobDTO.setReview(reviews);
		return jobDTO;
	}
}
