package com.spring.cloud.jpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "TASK_MASTER")
@Entity
public class Task {

    @Id
    @Column(name = "ID")
    private String taskId;

    @Column(name = "NAME")
    private String taskName;
}
