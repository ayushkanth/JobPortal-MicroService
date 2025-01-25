package com.learn.jobms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.jobms.dto.JobDTO;
import com.learn.jobms.entity.Company;
import com.learn.jobms.entity.Job;
import com.learn.jobms.entity.Review;
import com.learn.jobms.mapper.JobMapper;
import com.learn.jobms.repo.JobRepository;
import com.learn.jobms.service.JobService;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class JobServiceImpl implements JobService{
	
	private final JobRepository jobRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<JobDTO> findAll() {
		List<Job> jobs = jobRepository.findAll();
		List<JobDTO> jobDTOs= new ArrayList<>();
		
		for(Job job: jobs)
		{
			JobDTO jobDto = convertToDto(job);
			jobDTOs.add(jobDto);
		}
		return jobDTOs;
	}
	
	public JobDTO convertToDto(Job job)
	{
		Company company = restTemplate.getForObject(
				"http://COMPANYMS:8081/companies/"+job.getCompanyId(),
				Company.class);
		ResponseEntity<List<Review>> reviewResponse = restTemplate.
				exchange("http://REVIEWMS:8083/reviews?companyId="+job.getCompanyId(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Review>>() {
				});
		List<Review> reviews = reviewResponse.getBody();
		JobDTO jobDTO = JobMapper.mapToJobDTO(job, company,reviews);
		return jobDTO;
	}

	@Override
	public void createJob(Job job) {
		 jobRepository.save(job);		
	}

	@Override
	public JobDTO getJobById(Long id) {
		Job job= jobRepository.findById(id).orElse(null);
		return convertToDto(job);
	}

	@Override
	public boolean deleteJobById(Long id) {
		try {
			jobRepository.deleteById(id);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean updateJobById(Long id, Job updateJob) {
		Optional<Job> jobOptional = jobRepository.findById(id);
		if(jobOptional.isPresent())
		{
			Job job = jobOptional.get();
			job.setDescription(updateJob.getDescription());
			job.setLocation(updateJob.getLocation());
			job.setMaxSalary(updateJob.getMaxSalary());
			job.setMinSalary(updateJob.getMinSalary());
			job.setTitle(updateJob.getTitle());
			jobRepository.save(job);
			return true;
		}
		return false;
	}

}
