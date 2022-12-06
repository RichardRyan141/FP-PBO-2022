package com.example.fpcashier;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemsModel {
    private SimpleStringProperty itemName;
    private SimpleIntegerProperty itemQty;
    private SimpleDoubleProperty itemPrice;
    private SimpleDoubleProperty tableTotalPrice;

    public ItemsModel(String name, Integer qty, Double price, Double totalPrice)
    {
        this.itemName = new SimpleStringProperty(name);
        this.itemQty = new SimpleIntegerProperty(qty);
        this.itemPrice = new SimpleDoubleProperty(price);
        this.tableTotalPrice = new SimpleDoubleProperty(totalPrice);

        /*this.setItemName(new SimpleStringProperty(name));
        this.setItemQty(new SimpleIntegerProperty(qty));
        this.setItemPrice(new SimpleDoubleProperty(price));
        this.setTableTotalPrice(new SimpleDoubleProperty(totalPrice));*/
    }

    public int getItemQty() {
        return itemQty.get();
    }

    public void setItemQty(int itemQty) {
        this.itemQty = new SimpleIntegerProperty(itemQty);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }

    public double getItemPrice() {
        return itemPrice.get();
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
    }

    public double getTableTotalPrice() {
        return tableTotalPrice.get();
    }

    public void setTableTotalPrice(double tableTotalPrice) {
        this.tableTotalPrice = new SimpleDoubleProperty(tableTotalPrice);
    }
}
