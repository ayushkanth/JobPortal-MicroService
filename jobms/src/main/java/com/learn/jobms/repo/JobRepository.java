package com.learn.jobms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.jobms.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

}
