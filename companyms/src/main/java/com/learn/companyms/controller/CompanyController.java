package com.learn.companyms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.companyms.entity.Company;
import com.learn.companyms.service.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/companies") @RequiredArgsConstructor
public class CompanyController {
	
	private final CompanyService companyService;
	
	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies()
	{
		List<Company> allCompanies = companyService.getAllCompanies();
		return new ResponseEntity<List<Company>>(allCompanies,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createCompany(@RequestBody Company company)
	{
		companyService.createCompany(company);
		return new ResponseEntity<String>("Company Added Successfully!!",HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getJobById(@PathVariable Long id)
	{
		Company company = companyService.getCompanyById(id);
		if(company!=null) {
			return new ResponseEntity<Company>(company,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id)
	{
		boolean deleted = companyService.deleteCompanyById(id);
		if(deleted)
		{
			return new ResponseEntity<String>("Company deleted!!", HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Company updatedCompany)
	{
		boolean updated = companyService.updateCompany(id, updatedCompany);
		if(updated)
		{
			return new ResponseEntity<String>("Company updated!!",HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
}
