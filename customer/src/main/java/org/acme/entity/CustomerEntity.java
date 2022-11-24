package org.acme.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="customer")
public class CustomerEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String Phone;

    private String email;

    private Long age;

}
