package com.example.midterm_frost;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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

        // Load products from JSON

        JSONparserBeauty parser = new JSONparserBeauty(this);
        allProducts = parser.loadproductsfromjson();


        // **Filter Featured Products**
        featuredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if ("Hot".equalsIgnoreCase(product.getCategory()) || "Loved".equalsIgnoreCase(product.getCategory())) {
                featuredProducts.add(product);
            }
        }

        // **Filter Popular Products**
        popularProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getRating() >= 4.5f) {
                popularProducts.add(product);
            }
        }

        // Set adapters
        featuredAdapter = new ProductAdapter(featuredProducts, this);
        popularAdapter = new ProductAdapter(popularProducts, this);
        featuredProductsList.setAdapter(featuredAdapter);
        popularProductsList.setAdapter(popularAdapter);

        findViewById(R.id.cart_icon).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
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
