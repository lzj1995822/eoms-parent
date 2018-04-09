package com.distance.cma.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "cma_user")
public class User extends BaseEntity<String> {

    private String name;

    private String code;
}
