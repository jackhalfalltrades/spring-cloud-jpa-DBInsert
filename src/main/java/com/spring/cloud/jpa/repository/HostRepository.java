package com.spring.cloud.jpa.repository;

import com.spring.cloud.jpa.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("hostRepository")
public interface HostRepository extends JpaRepository<Host, String> {
}
