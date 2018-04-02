package com.spring.cloud.jpa.repository;

import com.spring.cloud.jpa.model.InfrastructuremMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("infrastructureMappingRepository")
public interface InfrastructureMappingRepository extends JpaRepository<InfrastructuremMapping, String> {
}
