package com.dc.dto;


public class VendorResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private Long createdByUserID;
    private String createdDate;
    private boolean active;
    private String activationEndDate;
    private Long maxNoOfUsers;

    public Long getId() {
        return id;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationEndDate() {
        return activationEndDate;
    }

    public void setActivationEndDate(String activationEndDate) {
        this.activationEndDate = activationEndDate;
    }

    public Long getMaxNoOfUsers() {
        return maxNoOfUsers;
    }

    public void setMaxNoOfUsers(Long maxNoOfUsers) {
        this.maxNoOfUsers = maxNoOfUsers;
    }
}

