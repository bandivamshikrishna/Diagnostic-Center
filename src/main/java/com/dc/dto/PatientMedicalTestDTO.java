package com.dc.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PatientMedicalTestDTO {
    @NotNull(message = "Test ID is required")
    private Long id;

    private Double discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
