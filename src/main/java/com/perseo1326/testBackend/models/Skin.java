package com.perseo1326.testBackend.models;

public class Skin {

    private String skinId;

    private String name;

    private String type;

    private double price;

    private String color;

    private Boolean active;

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
