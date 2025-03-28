package com.example.midterm_frost;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONPARSER extends AppCompatActivity {

    ArrayList<String> personName = new ArrayList<>();
    ArrayList<String> personEmail = new ArrayList<>();

    RecyclerView recycler;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonparser);

        recycler = findViewById(R.id.recycler);
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recycler.setLayoutManager(LinearLayoutManager);

        try{
            // get Json
            JSONObject jsonObject = new JSONObject(loadJsonFromAsset());

            JSONArray userArray = jsonObject.getJSONArray("users");

            // implement For Loop for getting the data from json File

            for(int i = 0; i < userArray.length(); i++){
                JSONObject userDetail = userArray.getJSONObject(i);
                personName.add(userDetail.getString("name"));
                personEmail.add(userDetail.getString("email"));
            }
        }catch(JSONException e) {
        }

        CustomAdapter customAdapter = new CustomAdapter(JSONPARSER.this,
                personName,personEmail);
        recycler.setAdapter(customAdapter);
    }

    private String loadJsonFromAsset() {
        String json = null;

        try{
            InputStream is = getAssets().open("user.json");
                    int size = is.available();
                    byte[]  buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
