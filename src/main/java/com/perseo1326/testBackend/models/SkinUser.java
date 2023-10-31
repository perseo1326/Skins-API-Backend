package com.perseo1326.testBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "SKIN_USERS")
public class SkinUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skin_users_id", nullable = false)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String skinId;

    public SkinUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSkinId() {
        return skinId;
    }

    public void setSkinId(String skinId) {
        this.skinId = skinId;
    }

    @Override
    public String toString() {
        return "SkinUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", skinId='" + skinId + '\'' +
                '}';
    }
}
