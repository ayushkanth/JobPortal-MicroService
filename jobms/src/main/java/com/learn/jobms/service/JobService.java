package com.learn.jobms.service;

import java.util.List;

import com.learn.jobms.dto.JobWithCompanyDTO;
import com.learn.jobms.entity.Job;

public interface JobService {

	public List<JobWithCompanyDTO> findAll();
	
	public void createJob(Job job);
	
	public Job getJobById(Long id);
	
	public boolean deleteJobById(Long id);
	
	public boolean updateJobById(Long id, Job updateJob);
	
 }
