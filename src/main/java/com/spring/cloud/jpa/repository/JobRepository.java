package com.spring.cloud.jpa.repository;

import com.spring.cloud.jpa.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jobRepository")
public interface JobRepository extends JpaRepository<Job, String> {
}
