package com.learn.companyms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.companyms.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long>{

}
