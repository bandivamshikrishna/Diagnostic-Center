package com.dc.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicalTestCreateRequestDTO {

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Test Name is required")
    private String testName;

    @NotBlank(message = "Normal Range is required")
    private String normalRange;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotNull(message = "Created By User ID is required")
    private Long createdByUserID;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getNormalRange() {
        return normalRange;
    }

    public void setNormalRange(String normalRange) {
        this.normalRange = normalRange;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(Long createdByUserID) {
        this.createdByUserID = createdByUserID;
    }
}
