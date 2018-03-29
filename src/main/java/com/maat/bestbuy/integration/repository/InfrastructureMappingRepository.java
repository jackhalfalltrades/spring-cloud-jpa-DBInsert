package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.InfrastructuremMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("infrastructureMappingRepository")
public interface InfrastructureMappingRepository extends JpaRepository<InfrastructuremMapping,String> {
}
