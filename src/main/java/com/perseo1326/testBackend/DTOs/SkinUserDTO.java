package com.perseo1326.testBackend.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public class SkinUserDTO {

    @NotNull(message = "A user id must be provided!")
    private int userid;

    @NotBlank(message = "A skin id must be provided!")
    private String skinid;

    private Optional<String> color;

    public SkinUserDTO() {
    }

    public SkinUserDTO(int userid, String skinid) {
        this.userid = userid;
        this.skinid = skinid;
    }

    public SkinUserDTO(int userid, String skinid, Optional<String> color) {
        this.userid = userid;
        this.skinid = skinid;
        this.color = color;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getSkinid() {
        return skinid;
    }

    public void setSkinid(String skinid) {
        this.skinid = skinid;
    }

    public Optional<String> getColor() {
        return color;
    }

    public void setColor(Optional<String> color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Values SkinUserDTO{" +
                "userid=" + userid +
                ", skinid='" + skinid + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
