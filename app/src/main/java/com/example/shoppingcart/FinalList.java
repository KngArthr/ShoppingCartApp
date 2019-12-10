package com.example.shoppingcart;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class FinalList extends AppCompatActivity {

    private static final String TAG = "FinalList";

    private String userName;
    private String bankAccount;


    private String itemName;
    private String itemPrice;
    private String itemPriority;
    private String itemQuantity;
    private String itemUnit;


    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private ArrayList<ItemClass> itemList = new ArrayList<>();

    private ArrayList<ItemClass> itemListSorted = new ArrayList<>();

    private HashMap<Integer, ItemClass> itemListMap = new HashMap<>();

    private HashMap<Integer, Object> itemComponent = new HashMap<>();



    private ShoppingCart shoppingCart;

    private FilterData filterData;

    DatabaseReference myShoppingCarts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_list);
        Log.d(TAG, "onCreate: started.");

        filterData = new FilterData();

        userName = getIntent().getStringExtra("key_userName");

        try {
            shoppingCart = new ShoppingCart();
        } catch (UnsupportedFormatException e) {
            e.printStackTrace();
        };

        myShoppingCarts = FirebaseDatabase.getInstance().getReference().child("carts").child(userName);






        myShoppingCarts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                bankAccount = dataSnapshot.child("bankAccount").getValue().toString();

                userName = dataSnapshot.child("userName").getValue().toString();
                int iter = 0;

                itemList = (ArrayList<ItemClass>) dataSnapshot.child("itemList").getValue();

                for(int i = 0; i < itemList.size(); i++) {


                    itemName = (String) dataSnapshot.child("itemList").child(String.valueOf(i)).child("itemName").getValue();
                    itemPrice = String.valueOf(dataSnapshot.child("itemList").child(String.valueOf(i)).child("price").getValue());
                    itemPriority = String.valueOf(dataSnapshot.child("itemList").child(String.valueOf(i)).child("priority").getValue());
                    itemQuantity = String.valueOf(dataSnapshot.child("itemList").child(String.valueOf(i)).child("quantity").getValue());
                    itemUnit = (String) dataSnapshot.child("itemList").child(String.valueOf(i)).child("unit").getValue();

                    itemListSorted.add(new ItemClass(itemName, Integer.parseInt(itemPriority), Double.parseDouble(itemPrice), Integer.parseInt(itemQuantity), itemUnit));


                }



                iter = 0;


                shoppingCart.setUserName(userName);
                shoppingCart.setBankAccount(bankAccount);





                shoppingCart.setItemList(itemList);
                shoppingCart.setItemListSorted(filterData.bubbleSortByPriority(itemListSorted));

                initImageBitmaps();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
    @Override
    protected void onStart() {
        super.onStart();


        myShoppingCarts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }


    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.rvFinalList);
        RecyclerViewAdpterFinal adapter = new RecyclerViewAdpterFinal(shoppingCart.getItemListSorted(), mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
