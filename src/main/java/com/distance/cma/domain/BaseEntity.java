package com.distance.cma.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name = "ID")
    private ID id;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_AT")
    private Date createdAt;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_AT")
    private Date updateAt;
}
