package com.dc.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class VendorManageMedicalTestsDTO {

    @NotNull(message = "Vendor ID is required")
    private Long vendorID;

    @NotNull(message = "Medical Tests is required")
    private List<VendorMedicalTestsDTO> medicalTests;


    public Long getVendorID() {
        return vendorID;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public List<VendorMedicalTestsDTO> getMedicalTests() {
        return medicalTests;
    }

    public void setMedicalTests(List<VendorMedicalTestsDTO> medicalTests) {
        this.medicalTests = medicalTests;
    }
}
