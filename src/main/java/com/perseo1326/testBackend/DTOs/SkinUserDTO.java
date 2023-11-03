package com.perseo1326.testBackend.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SkinUserDTO {

    @Schema(name = "El Id del usuario", example = "12", required = true)
    @NotNull(message = "A user id must be provided!")
    private Long userid;

    @Schema(name = "El Id de la skin", example = "B2", required = true)
    @NotBlank(message = "A skin id must be provided!")
    private String skinid;

    @Schema(name = "El color que se le aplicará a la skin", example = "purple", required = false)
    private String color;

    public SkinUserDTO() {
    }

    public SkinUserDTO(Long userid, String skinid, String color) {
        this.userid = userid;
        this.skinid = skinid;
        this.color = color;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getSkinid() {
        return skinid;
    }

    public void setSkinid(String skinid) {
        this.skinid = skinid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
