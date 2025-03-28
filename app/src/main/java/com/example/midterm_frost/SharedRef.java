package com.example.midterm_frost;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SharedRef extends AppCompatActivity {

    EditText name, email;
    Button btnSave, btnbtnRead, btnClear;


    private static final String myProf = "myprof";
    private static final String Name = "nameKey";
    private static final String emailKey = "emailKeyValue";

    SharedPreferences MySharedPref;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared);

        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        btnSave = findViewById(R.id.btnSave);
        btnbtnRead = findViewById(R.id.btnRetrieve);
        btnClear = findViewById(R.id.btnClear);

        MySharedPref = getSharedPreferences(myProf, Context.MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname = name.getText().toString();
                String semail = email.getText().toString();

                SharedPreferences.Editor editor = MySharedPref.edit();
                editor.putString(Name, sname);
                editor.putString(emailKey, semail);
                editor.commit();

                Toast.makeText(getApplicationContext(), "SUCCESS",Toast.LENGTH_LONG).show();
            }
        });

        btnbtnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });

    }

    public void getData(){
        if (MySharedPref.contains((Name))) {
            name.setText(MySharedPref.getString(Name,""));

        }
        if(MySharedPref.contains(emailKey)){
            email.setText(MySharedPref.getString(emailKey,""));
        }
    }

    public void clearData() {
        SharedPreferences.Editor editor = MySharedPref.edit();
        editor.clear();
        editor.apply();

        name.setText("");
        email.setText("");

        Toast.makeText(getApplicationContext(), "Cleared Successfully", Toast.LENGTH_LONG).show();
    }



}
