package com.dc.dto;

import jakarta.validation.constraints.NotNull;

public class PatientPackageDTO {

    @NotNull(message = "id is required")
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
