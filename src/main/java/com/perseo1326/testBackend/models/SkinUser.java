package com.perseo1326.testBackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USER_SKINS")
public class SkinUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skinsUsers_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "skinsUsers_user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "skinsUsers_skin_id", nullable = false, length = 10)
    private String skinId;

    @NotNull
    @Column(name = "skinsUsers_skin_color", length = 20, nullable = true)
    private String skinColor;

    public SkinUser() {
    }

    public SkinUser(Long userId, String skinId, String skinColor) {
        this.userId = userId;
        this.skinId = skinId;
        this.skinColor = skinColor;
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

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    @Override
    public String toString() {
        return "SkinUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", skinId='" + skinId + '\'' +
                ", skinColor='" + skinColor + '\'' +
                '}';
    }
}
