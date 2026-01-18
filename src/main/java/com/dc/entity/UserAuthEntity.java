package com.dc.entity;

import com.dc.enums.RoleEnum;
import com.dc.exception.RoleNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity(name = "tbl_user_details")
public class UserAuthEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private Long roleID;

    @Column(nullable = false)
    private Long vendorID;

    @Column(nullable = false)
    private Long createdByUserID;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(name = "is_active",nullable = false)
    private Boolean active;

    @Column(name = "is_locked",nullable = false)
    private Boolean locked;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_"+RoleEnum.getRole(getRoleID());
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        if(!RoleEnum.isValid(roleID))
            throw new RoleNotFoundException(String.format("Invalid Role ID : %d", roleID));
        this.roleID = roleID;
    }

    public Long getVendorID() {
        return vendorID;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public Long getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(Long createdByUserID) {
        this.createdByUserID = createdByUserID;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
