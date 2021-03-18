package com.nguyenhoanglong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd, btnSearch;
    private EditText edUsername, edEmail, edPhone;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    AppDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = AppDatabase.getAppDatabase(this);

        edUsername = findViewById(R.id.edUsername);
        edEmail = findViewById(R.id.edEmail);
        edPhone = findViewById(R.id.edPhone);
        btnAdd = findViewById(R.id.btnAdd);
        btnSearch = findViewById(R.id.btnSearch);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnAdd.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            onRegister();
        } else if (v == btnSearch) {
            onSearch();
        } else {
            return;
        }
    }

    private void onSearch() {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);

    }

    private void onRegister() {
        if (edUsername.getText().toString().isEmpty()) {
            Toast.makeText(this,"Please enter Customer Name", Toast.LENGTH_LONG).show();
            return;
        } else if (edEmail.getText().toString().isEmpty()) {
            Toast.makeText(this,"Please enter Email Address", Toast.LENGTH_LONG).show();
            return;
        } else if (edPhone.getText().toString().isEmpty()) {
            Toast.makeText(this,"Please enter Phone Number", Toast.LENGTH_LONG).show();
            return;
        } else if (rgGender.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Please select Gender", Toast.LENGTH_LONG).show();
            return;
        } else {
            insertUser();
        }
    }

    private void insertUser() {
        UserEntity us = new UserEntity();
        us.username = edUsername.getText().toString();
        us.email = edEmail.getText().toString();
        us.phone = edPhone.getText().toString();
        us.gender = rgGender.getCheckedRadioButtonId();
        db.userDao().insertUser(us);
    }
}