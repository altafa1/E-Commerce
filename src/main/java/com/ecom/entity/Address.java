package com.ecom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false, length = 256)
    private String id;

    @Column(name = "house_no", nullable = false)
    private String houseNo;

    @Column(name = "area", nullable = false)
    private String area;

    @Column(name = "pincode",nullable=false)
    private Long pinCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

}