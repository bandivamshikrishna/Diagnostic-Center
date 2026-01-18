package com.dc.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "tbl_medical_test_details")
public class MedicalTestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false,unique = true)
    private String testName;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private String normalRange;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Long createdByUserID;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = true)
    private Long lastModifiedByUserID;

    @Column(nullable = true)
    private LocalDateTime lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(Long createdByUserID) {
        this.createdByUserID = createdByUserID;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedByUserID() {
        return lastModifiedByUserID;
    }

    public void setLastModifiedByUserID(Long lastModifiedByUserID) {
        this.lastModifiedByUserID = lastModifiedByUserID;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
