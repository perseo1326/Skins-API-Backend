package com.perseo1326.testBackend.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class Skin {

    @Schema(name = "Id de la skin", example = "C3", required = true)
    private String skinId;

    @Schema(name = "Nombre de la skin", example = "red basic skin", required = true)
    private String name;

    @Schema(name = "Tipo de skin", example = "basic", required = true)
    private String type;

    @Schema(name = "Precio de la skin", example = "9.99", required = true)
    private double price;

    @Schema(name = "Color que se aplica a la skin", example = "yellow", required = true)
    private String color;

    @Schema(name = "Propiedad que indica si la skin esta activa o no", example = "true", required = true)
    private Boolean active;

    @Schema(name = "Vresion de la skin", example = "1.0", required = true)
    private double skinVersion;

    public Skin() {
    }

    public Skin(String skinId, String name, String type, double price, String color, Boolean active, double skinVersion) {
        this.skinId = skinId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.color = color;
        this.active = active;
        this.skinVersion = skinVersion;
    }

    public String getSkinId() {
        return skinId;
    }

    public void setSkinId(String skinId) {
        this.skinId = skinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public double getSkinVersion() {
        return skinVersion;
    }

    public void setSkinVersion(double skinVersion) {
        this.skinVersion = skinVersion;
    }

    @Override
    public String toString() {
        return "Skin{" +
                "skinId='" + skinId + '\'' +
                ", name='" + name + '\'' +
                ", tipo='" + type + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", active=" + active +
                ", skinVersion=" + skinVersion +
                '}';
    }
}
