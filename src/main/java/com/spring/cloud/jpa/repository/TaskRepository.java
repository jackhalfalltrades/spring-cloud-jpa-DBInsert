package com.spring.cloud.jpa.repository;

import com.spring.cloud.jpa.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, String> {
}
