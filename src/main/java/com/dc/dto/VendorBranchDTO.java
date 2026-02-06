package com.dc.dto;

import jakarta.validation.constraints.NotBlank;

public class VendorBranchDTO {

    @NotBlank(message = "Branch Code is required")
    private String branchCode;

    @NotBlank(message = "Branch Name is required")
    private String branchName;

    @NotBlank(message = "Branch Address is required")
    private String branchAddress;


    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
