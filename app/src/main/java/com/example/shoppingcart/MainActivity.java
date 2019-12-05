package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String userName;
    String bankAccount;
    ShoppingCart shoppingCart;

    DatabaseReference myShoppingCarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            shoppingCart = new ShoppingCart();
        } catch (UnsupportedFormatException e) {
            e.printStackTrace();
        };

        myShoppingCarts = FirebaseDatabase.getInstance().getReference().child("carts");

        Button buttonProceed1 = (Button) findViewById(R.id.buttonProceed1);


        final EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        final EditText editTextBudget = (EditText) findViewById(R.id.editTextBudget);



        editTextUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editTextUserName.getText().clear(); //or you can use editText.setText("");

            }
        });

        editTextBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editTextBudget.getText().clear(); //or you can use editText.setText("");

            }
        });





        buttonProceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = editTextUserName.getText().toString();
                bankAccount = editTextBudget.getText().toString();


                if(userName.isEmpty() || bankAccount.isEmpty()){

                    Toast.makeText(getApplicationContext(), "Please make entries in all fields.", Toast.LENGTH_LONG).show();


                }else{

                    myShoppingCarts.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.child(userName).exists()){
                                shoppingCart.setUserName((String) dataSnapshot.child(userName).child("userName").getValue());
                                shoppingCart.setBankAccount(bankAccount);
                                myShoppingCarts.child(shoppingCart.getUserName()).child("bankAccount").setValue(bankAccount);




                            }
                            else{
                                shoppingCart.setUserName(userName);

                                shoppingCart.setBankAccount(bankAccount);

                                myShoppingCarts.child(shoppingCart.getUserName()).setValue(shoppingCart);
                            }

                            Toast.makeText(getApplicationContext(), "Created new shopping cart with Username: " + ((String) dataSnapshot.child(userName).child("userName").getValue()) + " and Budget: " + ((String) dataSnapshot.child(shoppingCart.getUserName()).child("bankAccount").getValue()), Toast.LENGTH_LONG).show();

                            Intent startIntent = new Intent(MainActivity.this, Central.class);

                            startIntent.putExtra("key_userName", shoppingCart.getUserName());

                            startActivity(startIntent);




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });











                }


            }
        });





    }



}
