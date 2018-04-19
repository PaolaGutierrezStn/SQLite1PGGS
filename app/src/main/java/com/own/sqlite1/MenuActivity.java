package com.own.sqlite1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.own.sqlite1.model.Supplier;

public class MenuActivity extends AppCompatActivity {

    public void nextActivity1(View view){
        startActivity(new Intent(this, CustomerActivity.class));
    }

    public void nextActivity2(View view){
        startActivity(new Intent(this, ProductActivity.class));
    }

    public void nextActivity3(View view){
        startActivity(new Intent(this, MethodActivity.class));
    }

    public void nextActivity4(View view){
        startActivity(new Intent(this, OrderActivity.class));
    }

    public void nextActivity5(View view){
        startActivity(new Intent(this, SupplierActivity.class));
    }

    public void nextActivity6(View view){
        startActivity(new Intent(this, PurchaseActivity.class));
    }

    public void nextActivity7(View view){
        startActivity(new Intent(this, BookActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
}
