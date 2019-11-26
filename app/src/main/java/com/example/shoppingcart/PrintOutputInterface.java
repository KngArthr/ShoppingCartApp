package com.example.shoppingcart;

import java.io.FileWriter;
import java.util.ArrayList;

public interface PrintOutputInterface {


    public void writeItemsToFile();

    public void printItemClassArrayToConsole(ArrayList<ItemClass> itemList);
    public void printShoppingCartToConsole(ShoppingCart shoppingCart);

    public void writeItemClassArrayToFile(ArrayList<ItemClass> itemList, FileWriter fileWriter);
    public void writeShoppingCartToFile(ShoppingCart shoppingCart, FileWriter fileWriter);


}
