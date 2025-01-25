package com.learn.jobms.service;

import java.util.List;

import com.learn.jobms.dto.JobDTO;
import com.learn.jobms.entity.Job;

public interface JobService {

	public List<JobDTO> findAll();
	
	public void createJob(Job job);
	
	public JobDTO getJobById(Long id);
	
	public boolean deleteJobById(Long id);
	
	public boolean updateJobById(Long id, Job updateJob);
	
 }
