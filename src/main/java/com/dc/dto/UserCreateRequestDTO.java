package com.dc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserCreateRequestDTO {

    @Email
    @NotBlank(message = "Email is required")
    public String email;

    @NotNull(message = "Role ID is required")
    private Long roleID;

    @NotNull(message = "Vendor ID is required")
    private Long vendorID;

    @NotNull(message = "Created By User ID is required")
    private Long createdByUserID;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Long getVendorID() {
        return vendorID;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public Long getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(Long createdByUserID) {
        this.createdByUserID = createdByUserID;
    }
}
