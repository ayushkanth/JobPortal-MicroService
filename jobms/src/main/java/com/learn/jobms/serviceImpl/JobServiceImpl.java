package com.learn.jobms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.jobms.dto.JobWithCompanyDTO;
import com.learn.jobms.entity.Company;
import com.learn.jobms.entity.Job;
import com.learn.jobms.repo.JobRepository;
import com.learn.jobms.service.JobService;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class JobServiceImpl implements JobService{

	private final JobRepository jobRepository;
	
	@Override
	public List<JobWithCompanyDTO> findAll() {
		List<Job> jobs = jobRepository.findAll();
		List<JobWithCompanyDTO> jobWithCompanyDTOs= new ArrayList<>();
		
		for(Job job: jobs)
		{
			JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
			jobWithCompanyDTO.setJob(job);
			RestTemplate restTemplate=new RestTemplate();
			Company company = restTemplate.getForObject(
					"http://localhost:8081/companies/"+job.getCompanyId(),
					Company.class);
			jobWithCompanyDTO.setCompany(company);
			jobWithCompanyDTOs.add(jobWithCompanyDTO);
		}
		return jobWithCompanyDTOs;
	}

	@Override
	public void createJob(Job job) {
		 jobRepository.save(job);		
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
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
