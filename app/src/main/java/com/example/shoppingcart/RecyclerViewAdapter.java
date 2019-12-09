package com.example.shoppingcart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<ItemClass> itemList = new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapter(ArrayList<ItemClass> itemList, ArrayList<String> mImages, Context mContext) {
        //this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);


        holder.itemName.setText(itemList.get(position).getItemName());
        holder.itemPrice.setText(String.valueOf(itemList.get(position).getPrice()));
        holder.itemQuantity.setText(itemList.get(position).getQuantity());
        holder.itemUnit.setText(itemList.get(position).getUnit());
        holder.itemFinalPrice.setText(String.valueOf(itemList.get(position)));
        holder.itemPriority.setText(String.valueOf(itemList.get(position)));



        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + itemList.get(position));

                Toast.makeText(mContext, itemList.get(position).getItemName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        CircleImageView image;
        TextView itemName;
        TextView itemPrice;
        TextView itemQuantity;
        TextView itemUnit;
        TextView itemFinalPrice;
        TextView itemPriority;



        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemUnit = itemView.findViewById(R.id.item_unit);
            itemFinalPrice = itemView.findViewById(R.id.item_finalPrice);
            itemPriority = itemView.findViewById(R.id.item_priority);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }


    }
}
