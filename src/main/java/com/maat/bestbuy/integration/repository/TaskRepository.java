package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, String> {
}
