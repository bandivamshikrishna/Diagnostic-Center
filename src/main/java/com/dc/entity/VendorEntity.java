package com.dc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;

@Entity(name = "tbl_vendor_details")
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Lob
    @Column(nullable = true)
    private byte[] logo;

    @Column(nullable = false)
    private Long createdByUserID;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = true)
    private Long lastModifiedByUserID;

    @Column(nullable = true)
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false, name = "is_active")
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime activationEndDate;

    @Column(nullable = false)
    private Long maxNoOfUsers;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCreatedByUserID() {
        return createdByUserID;
    }


    public void setCreatedByUserID(Long createdByUserID) {
        this.createdByUserID = createdByUserID;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedByUserID() {
        return lastModifiedByUserID;
    }

    public void setLastModifiedByUserID(Long lastModifiedByUserID) {
        this.lastModifiedByUserID = lastModifiedByUserID;
    }

    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getActivationEndDate() {
        return activationEndDate;
    }

    public void setActivationEndDate(LocalDateTime activationEndDate) {
        this.activationEndDate = activationEndDate;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Long getMaxNoOfUsers() {
        return maxNoOfUsers;
    }

    public void setMaxNoOfUsers(Long maxNoOfUsers) {
        this.maxNoOfUsers = maxNoOfUsers;
    }
}
