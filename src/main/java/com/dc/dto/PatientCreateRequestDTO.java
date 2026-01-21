package com.dc.dto;

import com.dc.enums.PatientStatusEnum;
import com.dc.validators.FinalValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;
import java.util.Date;

public class PatientCreateRequestDTO {

    @NotNull(message = "Title ID is required",groups = FinalValidation.class)
    private Long titleID;

    @NotBlank(message = "Patient name is required",groups = FinalValidation.class)
    private String patientName;

    @NotNull(message = "Date is required",groups = FinalValidation.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dob;

    @NotNull(message = "Gender is required",groups = FinalValidation.class)
    private Long genderID;

    private String email;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid Phone Number"
    )
    private String phoneNumber;


    private Long doctorID;


    @NotNull(message = "Sample Collected Date is required", groups = FinalValidation.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date sampleCollectedDate;

    @NotNull(message = "Sample Collected Time is required", groups = FinalValidation.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime sampleCollectedTime;

    @NotNull(message = "Reported Date is required", groups = FinalValidation.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date reportedDate;

    @NotNull(message = "Reported Time is required", groups = FinalValidation.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime reportedTime;

    @NotNull(message = "CreatedBy User ID is required",groups = FinalValidation.class)
    private Long createdByUserID;


    @NotNull(message = "Status is required")
    private PatientStatusEnum status;


    public Long getTitleID() {
        return titleID;
    }

    public void setTitleID(Long titleID) {
        this.titleID = titleID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getGenderID() {
        return genderID;
    }

    public void setGenderID(Long genderID) {
        this.genderID = genderID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }


    public Date getSampleCollectedDate() {
        return sampleCollectedDate;
    }

    public void setSampleCollectedDate(Date sampleCollectedDate) {
        this.sampleCollectedDate = sampleCollectedDate;
    }

    public LocalTime getSampleCollectedTime() {
        return sampleCollectedTime;
    }

    public void setSampleCollectedTime(LocalTime sampleCollectedTime) {
        this.sampleCollectedTime = sampleCollectedTime;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public LocalTime getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(LocalTime reportedTime) {
        this.reportedTime = reportedTime;
    }

    public Long getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(Long createdByUserID) {
        this.createdByUserID = createdByUserID;
    }

    public PatientStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PatientStatusEnum status) {
        this.status = status;
    }

}
