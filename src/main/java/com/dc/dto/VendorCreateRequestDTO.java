package com.dc.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public class VendorCreateRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email ID")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone Number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid Phone Number"
    )
    private String phoneNumber;

    @NotNull(message = "Logo is required")
    private MultipartFile logo;

    @NotNull(message = "Created By User ID is required")
    private Long createdByUserID;

    @NotBlank(message = "Activation End Date is required")
    private String activationEndDate;

    @NotNull(message = "Max No Of Users is required")
    private Long maxNoOfUsers;

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

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public Long getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(Long createdByUserID) {
        this.createdByUserID = createdByUserID;
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
