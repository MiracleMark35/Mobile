package com.example.midterm_frost;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class PersonInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);


        findViewById(R.id.cart_icon).setOnClickListener(v -> {
            Intent intent = new Intent(PersonInfoActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Home Icon Click Listener
        findViewById(R.id.home_icon).setOnClickListener(v -> {
            Intent intent = new Intent(PersonInfoActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });


        findViewById(R.id.my_explore).setOnClickListener(v -> {
            Intent intent = new Intent(PersonInfoActivity.this, ExploreActivity.class);
            startActivity(intent);
        });



    }



}
