package com.example.midterm_frost;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TextView totalAmount;
    private Button checkoutButton;
    private CartAdapter cartAdapter;
    private RecyclerView cartRecyclerView;
    private List<Product> cartItems; // Make cartItems an instance variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalAmount = findViewById(R.id.total_price);
        checkoutButton = findViewById(R.id.checkout_button);
        cartRecyclerView = findViewById(R.id.cart_recycler_view);

        cartItems = Cart.getCartItems(); // Initialize cart items

        cartAdapter = new CartAdapter(cartItems, this);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        updateTotalPrice();

        checkoutButton.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate total price and quantity
            double totalPrice = 0;
            int totalQuantity = cartItems.size(); // Assuming each product is counted as 1

            for (Product p : cartItems) {
                totalPrice += p.getPrice();
            }

            // Show a toast message with order details
            String orderSummary = String.format("Order Placed! \nTotal Items: %d\nTotal Price: $%.2f", totalQuantity, totalPrice);
            Toast.makeText(this, orderSummary, Toast.LENGTH_LONG).show();

            // Clear the cart properly
            Cart.clearCart();
            cartItems.clear(); // Update local reference
            cartAdapter.notifyDataSetChanged(); // Refresh UI
            updateTotalPrice(); // Update total price to 0
        });

        // Click Listener for Home Icon (reload HomeActivity)
        findViewById(R.id.home_icon).setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Prevents activity stacking
            startActivity(intent);
        });


        findViewById(R.id.my_explore).setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, ExploreActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.profile_icon).setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, PersonInfoActivity.class);
            startActivity(intent);
        });
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Product p : cartItems) {
            totalPrice += p.getPrice();
        }
        totalAmount.setText(String.format("Total: $%.2f", totalPrice));
    }
}
