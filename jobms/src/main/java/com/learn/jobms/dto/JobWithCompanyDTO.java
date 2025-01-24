package com.learn.jobms.dto;

import com.learn.jobms.entity.Company;
import com.learn.jobms.entity.Job;

import lombok.Data;

@Data
public class JobWithCompanyDTO {
	private Job job;
	private Company company;
}
