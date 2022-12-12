package com.example.fpcashier.product;

public abstract class Item {
    private String name;
    private double price;
    private int qty;

    public Item(String name, double price)
    {
        this.name = name;
        this.setPrice(price);
        this.setQty(0);
    }

    public abstract void updateData(double newPrice);
    public abstract void updateData(int newQty);


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void addQty(int x)
    {
        this.qty = this.qty+x;
    }
}
