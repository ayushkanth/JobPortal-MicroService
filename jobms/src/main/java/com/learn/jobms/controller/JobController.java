package com.learn.jobms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jobms.dto.JobDTO;
import com.learn.jobms.entity.Job;
import com.learn.jobms.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private JobService jobService;
	
	public JobController(JobService jobService)
	{
		this.jobService=jobService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<JobDTO>> getAllJobs()
	{
		return ResponseEntity.ok(jobService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job)
	{
		 jobService.createJob(job);
		 return new ResponseEntity<String>("Job Added Successfully!!",HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobDTO> getJobById(@PathVariable Long id)
	{
		JobDTO jobDto = jobService.getJobById(id);
		if(jobDto!=null) {
			return new ResponseEntity<JobDTO>(jobDto,HttpStatus.OK);
		}
		return new ResponseEntity<JobDTO>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id)
	{
		boolean deleted = jobService.deleteJobById(id);
		if(deleted)
		{
			return new ResponseEntity<String>("Job deleted!!", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob)
	{
		boolean updated = jobService.updateJobById(id, updatedJob);
		if(updated)
		{
			return new ResponseEntity<String>("Job updated!!",HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
}
