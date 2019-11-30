package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {
    String itemName;
    String itemPrice;
    String itemPriority;
    String itemQuantity;
    String itemUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Button buttonBack2 = (Button) findViewById(R.id.buttonBack2);

        Button buttonAddItemToCart = (Button) findViewById(R.id.buttonAddItemToCart);

        final EditText editTextItemName = (EditText) findViewById(R.id.editTextItemName);
        final EditText editTextItemPrice = (EditText) findViewById(R.id.editTextItemPrice);
        final EditText editTextItemPriority = (EditText) findViewById(R.id.editTextItemPriority);
        final EditText editTextItemQuantity = (EditText) findViewById(R.id.editTextItemQuantity);
        final EditText editTextItemUnit = (EditText) findViewById(R.id.editTextItemUnit);


        editTextItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextItemName.getText().clear(); //or you can use editText.setText("");

            }
        });

        editTextItemPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextItemPrice.getText().clear(); //or you can use editText.setText("");

            }
        });
        editTextItemPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextItemPriority.getText().clear(); //or you can use editText.setText("");

            }
        });

        editTextItemQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextItemQuantity.getText().clear(); //or you can use editText.setText("");

            }
        });
        editTextItemUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextItemUnit.getText().clear(); //or you can use editText.setText("");

            }
        });


        buttonAddItemToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextItemName = (EditText) findViewById(R.id.editTextItemName);
                EditText editTextItemPrice = (EditText) findViewById(R.id.editTextItemPrice);
                EditText editTextItemPriority = (EditText) findViewById(R.id.editTextItemPriority);
                EditText editTextItemQuantity = (EditText) findViewById(R.id.editTextItemQuantity);
                EditText editTextItemUnit = (EditText) findViewById(R.id.editTextItemUnit);


                itemName = String.valueOf(editTextItemName);
                itemPrice = String.valueOf(editTextItemPrice);
                itemPriority = String.valueOf(editTextItemPriority);
                itemQuantity = String.valueOf(editTextItemQuantity);
                itemUnit = String.valueOf(editTextItemUnit);



                Intent startIntent = new Intent(getApplicationContext(), Central.class);
               // startIntent.putExtra("com.Example.shoppingcart.userName", userName);
               //you are here startIntent.putExtra("com.Example.shoppingcart.bankAccount", bankAccount);

                startActivity(startIntent);




            }
        });

        buttonBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Central.class);
                startActivity(startIntent);

            }
        });

    }
}
