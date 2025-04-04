package com.example.midterm_frost;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONparserBeauty extends AppCompatActivity {
    private Context context; // Context is required to access resources

    public JSONparserBeauty(Context context) {
        this.context = context;
    }

    // Constructor - Takes a Context as a parameter so we can use it to load assets
    // or automatic


    public List<Product> loadproductsfromjson(){  // Thus create method to load products from JSON file

        ArrayList<Product> productList = new ArrayList<Product>(); // Create an empty list to store products

        // Open the JSON file from the assets folderr

        try{
            InputStream is = context.getAssets().open("products.json");
            // Read the entire file into a byte array
            int size = is.available(); //This tells us how big the file is.
            byte[] buffer = new byte[size]; //This array temporarily holds the file's raw data
            is.read(buffer);
            is.close();         // Close the InputStream after reading itta

            String json  = new String(buffer, StandardCharsets.UTF_8);   // Convert the byte array into a JSON string
            // Para readable

            JSONObject jsonObject = new JSONObject(json);  // Parse the JSON string into a JSONObject

            JSONArray productArray = jsonObject.getJSONArray("products");    // Get the "products" array from the JSON object


            for(int i = 0; i < productArray.length(); i++){  // Loop through each product in the JSON array
                JSONObject productObj = productArray.getJSONObject(i); // get object each iteration

                String name = productObj.getString("name");
                // Get the image resource ID dynamically using the image name
                int imageRes = context.getResources().getIdentifier(
                        productObj.getString("image"), "drawable", context.getPackageName()
                );

                double price = productObj.getDouble("price"); // Get the pricess
                float rating = (float) productObj.getDouble("rating"); // Get the ratingss
                String skinType = productObj.getString("skinType"); // Get the skin typess
                String category = productObj.getString("category"); // Get the categoryss

                // creat sss a new Product object and add it to the list
                productList.add(new Product(name, imageRes, price, rating, skinType, category));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print erroreees in case of failure
        }

        return productList; // Return the list of products
    }
}
