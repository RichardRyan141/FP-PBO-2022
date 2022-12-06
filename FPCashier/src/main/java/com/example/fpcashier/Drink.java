package com.example.fpcashier;

public class Drink<T> extends Item {

    public Drink(ItemNames name, double price)
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
    public void addQty()
    {
        super.addQty();
    }
}
