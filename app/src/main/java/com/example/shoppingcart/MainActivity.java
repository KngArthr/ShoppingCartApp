package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    String userName;
    String bankAccount;
    ShoppingCart shoppingCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            shoppingCart = new ShoppingCart();
        } catch (UnsupportedFormatException e) {
            e.printStackTrace();
        }

        Button buttonProceed1 = (Button) findViewById(R.id.buttonProceed1);

        final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        final EditText editTextBudget = (EditText) findViewById(R.id.editTextBudget);



        editTextUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUserName.getText().clear(); //or you can use editText.setText("");

            }
        });

        editTextBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextBudget.getText().clear(); //or you can use editText.setText("");

            }
        });


        buttonProceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
                EditText editTextBudget = (EditText) findViewById(R.id.editTextBudget);

                userName = String.valueOf(editTextUserName);
                bankAccount = (String.valueOf(editTextBudget));







                Intent startIntent = new Intent(MainActivity.this, Central.class);
                //Bundle bundle = new Bundle();
               // bundle.putSerializable("key_one", shoppingCart);
                //update this shit
                shoppingCart.setUserName(userName);
                shoppingCart.setBankAccount(bankAccount);
                startIntent.putExtra("key_two", shoppingCart);
                //startIntent.putExtra("key_two", shoppingCart);

                // startIntent.putExtras(bundle);
                //startIntent.putExtra("com.Example.shoppingcart.shoppingCart", shoppingCart);
                //startIntent.putExtra("com.Example.shoppingcart.bankAccount", bankAccount);

                startActivity(startIntent);


            }
        });





    }


}
