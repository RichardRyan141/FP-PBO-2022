package com.example.fpcashier.product;

public class Food<T> extends Item {

    public Food(String name, double price)
    {
        super(name,price);
    }

    @Override
    public void updateData(double newPrice) {
        super.setPrice(newPrice);
    }

    @Override
    public void updateData(int newQty) {
        super.setQty(newQty);
    }

    @Override
    public void addQty(int x)
    {
        super.addQty(x);
    }
}
