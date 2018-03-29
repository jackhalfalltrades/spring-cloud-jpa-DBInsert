package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("hostRepository")
public interface HostRepository extends JpaRepository<Host, String> {
}
