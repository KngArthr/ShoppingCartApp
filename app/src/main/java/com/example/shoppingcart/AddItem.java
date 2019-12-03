package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity{
    String itemName;
    String itemPrice;
    String itemPriority;
    String itemQuantity;
    String itemUnit;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myShoppingCarts = database.getReference("Carts");

    //ShoppingCart shoppingCart;
    ArrayList<ItemClass> itemList = new ArrayList<ItemClass>();


    protected void onStart() {

        super.onStart();

        myShoppingCarts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot cartSnapshot: dataSnapshot.getChildren()){
                    ShoppingCart shoppingCart = cartSnapshot.getValue(ShoppingCart.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


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
        Intent getIntent = getIntent();

        //shoppingCart = getIntent.getParcelableExtra("key_shoppingCart");


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



                itemName = editTextItemName.getText().toString();
                itemPrice = editTextItemPrice.getText().toString();
                itemPriority = editTextItemPriority.getText().toString();
                itemQuantity = editTextItemQuantity.getText().toString();
                itemUnit = editTextItemUnit.getText().toString();

               // myShoppingCarts.child(MainActivity.getId());
               // String id = MainActivity.getId();

                Bundle bundle = new Bundle();

                bundle.putString("key_itemName", itemName);
                bundle.putString("key_itemPrice", itemPrice);
                bundle.putString("key_itemPriority", itemPriority);
                bundle.putString("key_itemQuantity", itemQuantity);
                bundle.putString("key_itemUnit", itemUnit);



                Intent startIntent = new Intent(getApplicationContext(), Central.class);

                itemList.add(new ItemClass(itemName, Integer.parseInt(itemPriority), Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity), itemUnit));

                //startIntent.putExtra("key_bundle", bundle);

                //shoppingCart.setItemList(itemList);

               // startIntent.putExtra("key_shoppingCart", shoppingCart);


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
