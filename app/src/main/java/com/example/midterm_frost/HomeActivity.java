package com.example.midterm_frost;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView featuredProductsList, popularProductsList;
    private ProductAdapter featuredAdapter, popularAdapter;
    private List<Product> allProducts, featuredProducts, popularProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        featuredProductsList = findViewById(R.id.featured_products_list);
        popularProductsList = findViewById(R.id.popular_products_list);

        featuredProductsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        popularProductsList.setLayoutManager(new LinearLayoutManager(this));

        // Full Product List
        allProducts = new ArrayList<>();
        allProducts.add(new Product("Face Wash", R.drawable.product1, 19.99, 4.5f, "Normal", "Ranked"));
        allProducts.add(new Product("Moisturizer", R.drawable.product2, 29.99, 4.0f, "Dry", "Hot"));
        allProducts.add(new Product("Sunscreen", R.drawable.product3, 15.99, 4.8f, "Oily", "Loved"));
        allProducts.add(new Product("Serum", R.drawable.product4, 22.99, 4.2f, "Combination", "Secret"));
        allProducts.add(new Product("Clay Mask", R.drawable.product5, 18.50, 4.7f, "Oily", "Ranked"));
        allProducts.add(new Product("Hydrating Cream", R.drawable.product6, 25.50, 4.3f, "Normal", "Hot"));
        allProducts.add(new Product("Vitamin C Serum", R.drawable.product7, 30.99, 4.6f, "All", "Secret"));
        allProducts.add(new Product("Aloe Vera Gel", R.drawable.product8, 12.50, 4.4f, "Sensitive", "Loved"));
        allProducts.add(new Product("Night Repair Cream", R.drawable.product10, 35.99, 4.9f, "Dry", "Hot"));
        allProducts.add(new Product("Toner", R.drawable.product9, 14.99, 4.1f, "Combination", "Ranked"));
        allProducts.add(new Product("Lip Balm", R.drawable.product11, 8.99, 4.7f, "All", "Loved"));
        allProducts.add(new Product("Cleansing Oil", R.drawable.product12, 24.99, 4.5f, "Oily", "Secret"));


        // **Filter Featured Products** (Example: Products with category "Hot" or "Loved")
        featuredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategory().equalsIgnoreCase("Hot") || product.getCategory().equalsIgnoreCase("Loved")) {
                featuredProducts.add(product);
            }
        }

        // **Filter Popular Products** (Example: Products with rating >= 4.5)
        popularProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getRating() >= 4.5f) {
                popularProducts.add(product);
            }
        }

        // Set adapters with the filtered lists
        featuredAdapter = new ProductAdapter(featuredProducts, this);
        popularAdapter = new ProductAdapter(popularProducts, this);
        featuredProductsList.setAdapter(featuredAdapter);
        popularProductsList.setAdapter(popularAdapter);

        // Click Listeners for Bottom Navigation
        findViewById(R.id.cart_icon).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.home_icon).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        findViewById(R.id.my_explore).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ExploreActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.profile_icon).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PersonInfoActivity.class);
            startActivity(intent);
        });
    }
}
