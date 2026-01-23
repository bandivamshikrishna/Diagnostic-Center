package com.dc.dto;

import jakarta.validation.constraints.NotNull;

public class VendorMedicalTestsDTO {

    @NotNull(message = "Medical Test ID is required")
    private Long medicalTestID;

    @NotNull(message = "Medical Test Price is required")
    private Double testPrice;


    public Long getMedicalTestID() {
        return medicalTestID;
    }

    public void setMedicalTestID(Long medicalTestID) {
        this.medicalTestID = medicalTestID;
    }

    public Double getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(Double testPrice) {
        this.testPrice = testPrice;
    }
}
