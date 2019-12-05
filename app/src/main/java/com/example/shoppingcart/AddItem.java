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

import java.util.ArrayList;

public class AddItem extends AppCompatActivity{

    Button buttonBack2;

    Button buttonAddItemToCart;

    EditText editTextItemName;
    EditText editTextItemPrice;
    EditText editTextItemPriority;
    EditText editTextItemQuantity;
    EditText editTextItemUnit;

    String itemName;
    String itemPrice;
    String itemPriority;
    String itemQuantity;
    String itemUnit;

    String userName;


    ShoppingCart shoppingCart;


    DatabaseReference myShoppingCarts;



    ArrayList<ItemClass> itemList = new ArrayList<ItemClass>();


    protected void onStart() {

        super.onStart();






    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        userName = getIntent().getStringExtra("key_userName");


        try {
            shoppingCart = new ShoppingCart();
        } catch (UnsupportedFormatException e) {
            e.printStackTrace();
        };

        shoppingCart.setUserName(userName);

        myShoppingCarts = FirebaseDatabase.getInstance().getReference().child("carts").child(userName);

        buttonBack2 = (Button) findViewById(R.id.buttonBack2);

        buttonAddItemToCart = (Button) findViewById(R.id.buttonAddItemToCart);

        editTextItemName = (EditText) findViewById(R.id.editTextItemName);
        editTextItemPrice = (EditText) findViewById(R.id.editTextItemPrice);
        editTextItemPriority = (EditText) findViewById(R.id.editTextItemPriority);
        editTextItemQuantity = (EditText) findViewById(R.id.editTextItemQuantity);
        editTextItemUnit = (EditText) findViewById(R.id.editTextItemUnit);

        myShoppingCarts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                if((ArrayList<ItemClass>) dataSnapshot.child("itemList").getValue() != null){
                    itemList = (ArrayList<ItemClass>) dataSnapshot.child("itemList").getValue();

                }

                buttonAddItemToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        itemName = editTextItemName.getText().toString();
                        itemPrice = editTextItemPrice.getText().toString();
                        itemPriority = editTextItemPriority.getText().toString();
                        itemQuantity = editTextItemQuantity.getText().toString();
                        itemUnit = editTextItemUnit.getText().toString();


                        if(itemName.isEmpty() || itemPrice.isEmpty() || itemPriority.isEmpty() || itemQuantity.isEmpty() || itemUnit.isEmpty()){

                            Toast.makeText(getApplicationContext(), "Please make entries in all fields.", Toast.LENGTH_LONG).show();


                        }else {


                            itemList.add(new ItemClass(itemName, Integer.parseInt(itemPriority), Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity), itemUnit));

                            myShoppingCarts.child("itemList").setValue(itemList);


                            Intent startIntent = new Intent(getApplicationContext(), Central.class);

                            startIntent.putExtra("key_userName", shoppingCart.getUserName());


                            //itemList.add(new ItemClass(itemName, Integer.parseInt(itemPriority), Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity), itemUnit));


                            startActivity(startIntent);
                        }




                    }
                });

               /* for(int i = 0; i < itemList.size(); i++){
                    itemList.get(i).setItemName(dataSnapshot.child("itemList").getValue().toString());
                    itemList.get(i).setPrice(Double.parseDouble(dataSnapshot.child("itemList").getValue()));
                    itemList.get(i).setPriority(dataSnapshot.child("itemList").getValue().toString());
                    itemList.get(i).setQuantity(dataSnapshot.child("itemList").getValue().toString());
                    itemList.get(i).setUnit(dataSnapshot.child("itemList").getValue().toString());

                }*/
                //textViewbankAccount.setText(bankAccount + "");

                //textViewUsername.setText(userName + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        editTextItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editTextItemName.getText().clear(); //or you can use editText.setText("");

            }
        });

        editTextItemPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        editTextItemPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editTextItemQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        editTextItemUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


       /* buttonAddItemToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                Intent startIntent = new Intent(getApplicationContext(), Central.class);

                startIntent.putExtra("key_userName", shoppingCart.getUserName());


                //itemList.add(new ItemClass(itemName, Integer.parseInt(itemPriority), Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity), itemUnit));


                startActivity(startIntent);




            }
        });*/

        buttonBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Central.class);
                startIntent.putExtra("key_userName", shoppingCart.getUserName());

                startActivity(startIntent);

            }
        });

    }
}
