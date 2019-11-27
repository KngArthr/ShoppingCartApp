package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Central extends AppCompatActivity {
    String userName;
    String bankAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);


        if(getIntent().hasExtra("com.Example.shoppingcart.userName")) {
         //   userName = getIntent().getExtras().getString("com.Example.shoppingcart.userName");
        }

        if(getIntent().hasExtra("com.Example.shoppingcart.bankAccount")){
           // bankAccount = getIntent().getExtras().getString("com.Example.shoppingcart.bankAccount");
        }

        Button buttonAddItemView = (Button) findViewById(R.id.buttonAddItemView);
        Button buttonItemList = (Button) findViewById(R.id.buttonItemList);
        Button buttonBuyItems = (Button) findViewById(R.id.buttonBuyItems);
        Button buttonBack1 = (Button) findViewById(R.id.buttonBack1);

        buttonAddItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), AddItem.class);
                startActivity(startIntent);


            }
        });

        buttonItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), ItemsListScroll.class);
                startIntent.putExtra("com.Example.shoppingcart.userName", userName);
                startIntent.putExtra("com.Example.shoppingcart.bankAccount", bankAccount);
                startActivity(startIntent);

            }
        });

        buttonBuyItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), FinalList.class);
                startIntent.putExtra("com.Example.shoppingcart.userName", userName);
                startIntent.putExtra("com.Example.shoppingcart.bankAccount", bankAccount);
                startActivity(startIntent);

            }
        });

        buttonBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);

            }
        });





        }
}
