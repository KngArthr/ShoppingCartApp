package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String userName;
    String bankAccount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button buttonProceed1 = (Button) findViewById(R.id.buttonProceed1);



        buttonProceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
                EditText editTextBudget = (EditText) findViewById(R.id.editTextBudget);

                userName = String.valueOf(editTextUserName);
                bankAccount = (String.valueOf(editTextBudget));

                //shoppingCart.setUserName(String.valueOf(editTextUserName));
                //shoppingCart.setBankAccount(String.valueOf(editTextBudget));

                Intent startIntent = new Intent(getApplicationContext(), Central.class);
                startIntent.putExtra("com.Example.shoppingcart.userName", userName);
                startIntent.putExtra("com.Example.shoppingcart.bankAccount", bankAccount);

                startActivity(startIntent);




            }
        });




    }


}
