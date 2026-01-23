package com.dc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tbl_vendor_details")
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Lob
    @Column(nullable = true)
    private byte[] logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id",nullable = false)
    private UserAuthEntity createdByUserID;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_modified_by_user_id", referencedColumnName = "id")
    private UserAuthEntity lastModifiedByUserID;

    @Column(nullable = true)
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false, name = "is_active")
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime activationEndDate;

    @Column(nullable = false)
    private Long maxNoOfUsers;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "vendor")
    private List<VendorMedicalTestEntity> medicalTest;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "vendor")
    private List<VendorPackageEntity> packages;









    //getters an setters

    public Long getId() {
        return this.id;
    }

    public List<VendorMedicalTestEntity> getMedicalTest() {
        return medicalTest;
    }

    public void setMedicalTest(List<VendorMedicalTestEntity> medicalTest) {
        this.medicalTest = medicalTest;
    }

    public List<VendorPackageEntity> getPackages() {
        return packages;
    }

    public void setPackages(List<VendorPackageEntity> packages) {
        this.packages = packages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserAuthEntity getCreatedByUserID() {
        return createdByUserID;
    }


    public void setCreatedByUserID(UserAuthEntity createdByUserID) {
        this.createdByUserID = createdByUserID;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public UserAuthEntity getLastModifiedByUserID() {
        return lastModifiedByUserID;
    }

    public void setLastModifiedByUserID(UserAuthEntity lastModifiedByUserID) {
        this.lastModifiedByUserID = lastModifiedByUserID;
    }

    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getActivationEndDate() {
        return activationEndDate;
    }

    public void setActivationEndDate(LocalDateTime activationEndDate) {
        this.activationEndDate = activationEndDate;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Long getMaxNoOfUsers() {
        return maxNoOfUsers;
    }

    public void setMaxNoOfUsers(Long maxNoOfUsers) {
        this.maxNoOfUsers = maxNoOfUsers;
    }
}
