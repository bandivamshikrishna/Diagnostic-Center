package com.dc.entity;

import jakarta.persistence.*;

@Entity(name = "tbl_roles_details")
public class UserRolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String roleName;
}
