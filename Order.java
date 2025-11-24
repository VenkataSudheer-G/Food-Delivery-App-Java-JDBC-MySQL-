package com.Food_Delivery_App;

public class Order {

    private int orderId;
    private String foodName;
    private int qty;
    private double totalPrice;

    public Order(int orderId, String foodName, int qty, double totalPrice) {
        this.orderId = orderId;
        this.foodName = foodName;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return orderId + " | " + foodName + " | Qty: " + qty + " | Total: ₹" + totalPrice;
    }
}
