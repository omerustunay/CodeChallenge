package com.example.dovizuygulamasi.Models;

public class ApiModel {

    private int id;
    private String symbol;
    private String name;
    private String description;
    private String color;
    private String iconType;
    private String iconUrl;
    private String change;
    private String price;

    public ApiModel() {
        super();
    }

    public ApiModel(int id, String symbol, String name, String description, String color, String iconType, String iconUrl, String price, String change) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.color = color;
        this.iconType = iconType;
        this.iconUrl = iconUrl;
        this.price = price;
        this.change = change;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "BorsaDeger{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", iconType='" + iconType + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", price='" + price + '\'' +
                ", change='" + change + '\'' +
                '}';
    }
}
