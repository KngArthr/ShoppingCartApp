package com.example.shoppingcart;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ItemsListScroll extends AppCompatActivity {


    private static final String TAG = "ItemsListScroll";

    private String userName;
    private String bankAccount;

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    private ArrayList<ItemClass> itemList = new ArrayList<>();

    private ArrayList<ItemClass> itemListSorted = new ArrayList<>();

    private ShoppingCart shoppingCart;

    private FilterData filterData;

    DatabaseReference myShoppingCarts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list_scroll);
        Log.d(TAG, "onCreate: started.");

        filterData = new FilterData();

        userName = getIntent().getStringExtra("key_userName");

        try {
            shoppingCart = new ShoppingCart();
        } catch (UnsupportedFormatException e) {
            e.printStackTrace();
        };

        myShoppingCarts = FirebaseDatabase.getInstance().getReference().child("carts").child(userName);



        myShoppingCarts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                bankAccount = dataSnapshot.child("bankAccount").getValue().toString();

                userName = dataSnapshot.child("userName").getValue().toString();

                itemList = (ArrayList<ItemClass>) dataSnapshot.child("itemList").getValue();

                shoppingCart.setUserName(userName);
                shoppingCart.setBankAccount(bankAccount);
                shoppingCart.setItemList(itemList);


                shoppingCart.setItemListSorted(filterData.bubbleSortByPriority(itemList));

                itemListSorted = shoppingCart.getItemListSorted();

                myShoppingCarts.child(shoppingCart.getUserName()).child("itemListSorted").setValue(itemListSorted);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        initImageBitmaps();

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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemListSorted, mImageUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
