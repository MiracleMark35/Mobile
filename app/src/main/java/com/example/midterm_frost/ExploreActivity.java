package com.example.midterm_frost;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {
    private RecyclerView exploreProductsList;
    private ProductAdapter exploreAdapter;
    private List<Product> allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        exploreProductsList = findViewById(R.id.explore_list22);
        exploreProductsList.setLayoutManager(new LinearLayoutManager(this));

        // Sample Product Data
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


        exploreAdapter = new ProductAdapter(new ArrayList<>(allProducts), this);
        exploreProductsList.setAdapter(exploreAdapter);

        // Skin Type Filters
        findViewById(R.id.normal_skin).setOnClickListener(v -> filterBySkinType("Normal"));
        findViewById(R.id.dry_skin).setOnClickListener(v -> filterBySkinType("Dry"));
        findViewById(R.id.oily_skin).setOnClickListener(v -> filterBySkinType("Oily"));
        findViewById(R.id.combination_skin).setOnClickListener(v -> filterBySkinType("Combination"));

        // New Category Filters
        findViewById(R.id.ranked_filter).setOnClickListener(v -> filterByCategory("Ranked"));
        findViewById(R.id.hot_filter).setOnClickListener(v -> filterByCategory("Hot"));
        findViewById(R.id.loved_filter).setOnClickListener(v -> filterByCategory("Loved"));
        findViewById(R.id.secret_filter).setOnClickListener(v -> filterByCategory("Secret"));

        // Click Listener for Home Icon
        findViewById(R.id.home_icon).setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        // Click Listener for Cart Icon
        findViewById(R.id.cart_icon).setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, CartActivity.class);
            startActivity(intent);
        });


        findViewById(R.id.profile_icon).setOnClickListener(v -> {
            Intent intent = new Intent(ExploreActivity.this, PersonInfoActivity.class);
            startActivity(intent);
        });





    }

    private void filterBySkinType(String skinType) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getSkinType().equalsIgnoreCase(skinType)) {
                filteredList.add(product);
            }
        }
        exploreAdapter.updateList(filteredList);
    }

    private void filterByCategory(String category) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredList.add(product);
            }
        }
        exploreAdapter.updateList(filteredList);
    }
}
