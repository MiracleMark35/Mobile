package com.example.midterm_frost;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        ImageView productImage = findViewById(R.id.product_image);
        TextView productName = findViewById(R.id.product_name);
        TextView productPrice = findViewById(R.id.product_price);
        TextView productRating = findViewById(R.id.product_rating);
        Button addToCart = findViewById(R.id.add_to_cart1);
        Button buyNow = findViewById(R.id.buy_now); // Buy Now button

        product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            productName.setText(product.getName());
            productPrice.setText(String.format("Price: $%.2f", product.getPrice()));
            productRating.setText(String.format("Rating: %.1f ★", product.getRating()));
            productImage.setImageResource(product.getImageResource());
        }

        addToCart.setOnClickListener(v -> Cart.addItem(product));

        buyNow.setOnClickListener(v -> {
            Cart.addItem(product); // Adds item to the cart before going to CartActivity
            Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Click Listener for Cart Icon
        findViewById(R.id.cart_icon).setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Click Listener for Home Icon (reload HomeActivity)
        findViewById(R.id.home_icon).setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailsActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Prevents activity stacking
            startActivity(intent);
        });

        findViewById(R.id.my_explore).setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailsActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Prevents activity stacking
            startActivity(intent);
        });

        findViewById(R.id.profile_icon).setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailsActivity.this, PersonInfoActivity.class);
            startActivity(intent);
        });

    }
}
