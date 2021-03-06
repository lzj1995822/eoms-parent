package com.eoms.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "eoms_user")
public class User extends BaseEntity<String> {

    private String name;

    private String password;

    private String code;

}
