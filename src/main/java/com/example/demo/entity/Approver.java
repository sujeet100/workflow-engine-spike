package com.example.demo.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Approver {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "user_id", nullable = true)
    private Integer userId;
    @Basic
    @Column(name = "approver_role", nullable = true, length = 50)
    private String approverRole;
    @Basic
    @Column(name = "city", nullable = true, length = 50)
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private AppUser appUserByUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getApproverRole() {
        return approverRole;
    }

    public void setApproverRole(String approverRole) {
        this.approverRole = approverRole;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String region) {
        this.city = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Approver approver = (Approver) o;
        return id == approver.id && Objects.equals(userId, approver.userId) && Objects.equals(
                approverRole,
                approver.approverRole) && Objects.equals(city, approver.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, approverRole, city);
    }

    public AppUser getAppUserByUserId() {
        return appUserByUserId;
    }

    public void setAppUserByUserId(AppUser appUserByUserId) {
        this.appUserByUserId = appUserByUserId;
    }
}
