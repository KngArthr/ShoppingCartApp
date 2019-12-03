package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Central extends AppCompatActivity {
    String userName;
    String bankAccount;

    DatabaseReference myShoppingCarts;

    String check;

    ShoppingCart shoppingCart;

    ArrayList<ItemClass> itemList = new ArrayList<ItemClass>();

    TextView textViewUsername;
    TextView textViewbankAccount;

    String itemName;
    String itemPrice;
    String itemPriority;
    String itemQuantity;
    String itemUnit;


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);

        userName = getIntent().getStringExtra("key_userName");

        try {
            shoppingCart = new ShoppingCart();
        } catch (UnsupportedFormatException e) {
            e.printStackTrace();
        };


        myShoppingCarts = FirebaseDatabase.getInstance().getReference().child("carts").child(userName);


        Button buttonAddItemView = (Button) findViewById(R.id.buttonAddItemView);
        Button buttonItemList = (Button) findViewById(R.id.buttonItemList);
        Button buttonBuyItems = (Button) findViewById(R.id.buttonBuyItems);

        Button buttonBack1 = (Button) findViewById(R.id.buttonBack1);



        textViewUsername = (TextView) findViewById((R.id.textViewUsername));
        textViewbankAccount = (TextView) findViewById((R.id.textViewbankAccount));




        myShoppingCarts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bankAccount = dataSnapshot.child("bankAccount").getValue().toString();

                userName = dataSnapshot.child("userName").getValue().toString();




               /* for(int i = 0; i < itemList.size(); i++){
                    itemList.get(i).setItemName(dataSnapshot.child("itemList").getValue().toString());
                    itemList.get(i).setPrice(Double.parseDouble(dataSnapshot.child("itemList").getValue()));
                    itemList.get(i).setPriority(dataSnapshot.child("itemList").getValue().toString());
                    itemList.get(i).setQuantity(dataSnapshot.child("itemList").getValue().toString());
                    itemList.get(i).setUnit(dataSnapshot.child("itemList").getValue().toString());

                }*/
                textViewbankAccount.setText(bankAccount + "");

                textViewUsername.setText(userName + "");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //itemList.add(new ItemClass(itemName, Integer.parseInt(itemPriority), Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity), itemUnit));
        //check = shoppingCart.getItemList().get(0).getItemName();
        check = userName;








        buttonAddItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent startIntent = new Intent(getApplicationContext(), AddItem.class);

                startIntent.putExtra("key_userName", shoppingCart.getUserName());


                startActivity(startIntent);


            }
        });

        buttonItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), ItemsListScroll.class);

                startIntent.putExtra("key_userName", shoppingCart.getUserName());



                startActivity(startIntent);

            }
        });

        buttonBuyItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), FinalList.class);

                startIntent.putExtra("key_userName", shoppingCart.getUserName());


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
