package com.example.midterm_frost;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final List<Product> cartItems = new ArrayList<>();

    public static void addItem(Product product) {
        cartItems.add(product);
    }

    public static List<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public static void removeItem(Product product) {
        cartItems.remove(product);
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
