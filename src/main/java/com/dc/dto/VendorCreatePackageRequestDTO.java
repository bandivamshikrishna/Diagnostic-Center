package com.dc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class VendorCreatePackageRequestDTO {

    @NotBlank(message = "Package name is required")
    private String name;

    @NotNull(message = "Vendor ID is required")
    private Long vendorID;

    @NotNull(message = "Package price is required")
    private Double packagePrice;

    @NotNull
    private List<MedicalTestsIDTO> medicalTests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVendorID() {
        return vendorID;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public Double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public List<MedicalTestsIDTO> getMedicalTests() {
        return medicalTests;
    }

    public void setMedicalTests(List<MedicalTestsIDTO> medicalTests) {
        this.medicalTests = medicalTests;
    }
}

