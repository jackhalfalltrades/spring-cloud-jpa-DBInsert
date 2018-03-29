package com.maat.bestbuy.integration.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "HOST_MASTER")
@Entity
public class Host {

    @Id
    @Column(name="ID")
    private String hostId;

    @Column(name="Name")
    private String hostName;
}
