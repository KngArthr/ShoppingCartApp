package com.example.shoppingcart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cashier implements CashierInterface, PrintOutputInterface{

    FilterData filterData = new FilterData();

    private File fileNameWrite;
    private ArrayList<ItemClass> itemsBought;
    private ArrayList<ItemClass> itemsRemaining;
    private ShoppingCart shoppingCart;
    private String bankAccountInitial;





    Cashier(ShoppingCart shoppingCart){

        //this.fileNameWrite = new File(fileWrite);
        this.shoppingCart = shoppingCart;

        itemsBought = new ArrayList<ItemClass>();
        itemsRemaining = new ArrayList<ItemClass>();
        bankAccountInitial = shoppingCart.getBankAccount();

        buyItems();
        //writeItemsToFile();


    }




    @Override
    public void buyItems() {
        int itemBoughtIteration = 0;
        int quantityRemaining = 0;
        int quantityBought = 0;

        //add items from item array to bought array. Deduct from budget based on what is bought
        for(int i = 0; i < shoppingCart.getItemListSorted().size(); i ++){

            //if the price is mroe or equal to bankaccount and the quantity is above zero, buy the item
            quantityRemaining = shoppingCart.getItemListSorted().get(i).getQuantity() - 1;
            quantityBought = 1;

            if((shoppingCart.getItemListSorted().get(i).getPrice() <= Double.parseDouble(shoppingCart.getBankAccount())) && (shoppingCart.getItemListSorted().get(i).getQuantity() > 0)) {

                if(shoppingCart.getItemListSorted().get(i).getQuantity() == 1) {
                    itemsBought.add(shoppingCart.getItemListSorted().get(i));
                    subtractBankAccount(String.valueOf(shoppingCart.getItemListSorted().get(i).getPrice()));


                }else {
                    //itemsBought.add(itemListSorted.get(i));
                    itemsBought.add(new ItemClass(shoppingCart.getItemListSorted().get(i).getItemName(), shoppingCart.getItemListSorted().get(i).getPriority(), shoppingCart.getItemListSorted().get(i).getPrice(), quantityBought, shoppingCart.getItemListSorted().get(i).getUnit()));
                    subtractBankAccount(String.valueOf(shoppingCart.getItemListSorted().get(i).getPrice()));
                    //bankAccountPost -= (shoppingCart.getItemListSorted().get(i).getPrice());

                    while(quantityRemaining > 0) {
                        if((shoppingCart.getItemListSorted().get(i).getPrice()) <= Double.parseDouble(shoppingCart.getBankAccount())) {
                            quantityBought++;

                            itemsBought.set(itemBoughtIteration, new ItemClass(shoppingCart.getItemListSorted().get(i).getItemName(), shoppingCart.getItemListSorted().get(i).getPriority(), shoppingCart.getItemListSorted().get(i).getPrice(), quantityBought, shoppingCart.getItemListSorted().get(i).getUnit()));

                            subtractBankAccount(String.valueOf(shoppingCart.getItemListSorted().get(i).getPrice()));
                            //bankAccountPost -= (shoppingCart.getItemListSorted().get(i).getPrice());
                            quantityRemaining--;
			    			/*System.out.println("Items Bought: Test");
	    			    	for(int j = 0; j < itemsBought.size(); j++) {
	    		    			System.out.println ("Slot " + (j + 1) + " contains item: " + itemsBought.get(j).getItemName() + " priority: " + itemsBought.get(j).getPriority() + " price: " + itemsBought.get(j).getPrice() + " Quantity: " + itemsBought.get(j).getQuantity() + " " + itemsBought.get(j).getUnit());

	    		    		}*/
                        }else {
                            itemsRemaining.add(new ItemClass(shoppingCart.getItemListSorted().get(i).getItemName(), shoppingCart.getItemListSorted().get(i).getPriority(), shoppingCart.getItemListSorted().get(i).getPrice(), quantityRemaining, shoppingCart.getItemListSorted().get(i).getUnit()));
                            break;
                        }
                    }

                }
                itemBoughtIteration++;

            }else {
                //those that are not bought get placed into the items remaining array
                itemsRemaining.add(shoppingCart.getItemListSorted().get(i));

            }

        }

        //print everything from items bought array
    	/*System.out.println("Items Bought:");
    	for(int i = 0; i < itemsBought.size(); i++) {
			System.out.println ("Slot " + (i + 1) + " contains item: " + itemsBought.get(i).getItemName() + " priority: " + itemsBought.get(i).getPriority() + " price: " + itemsBought.get(i).getPrice() + " Quantity: " + itemsBought.get(i).getQuantity() + " " + itemsBought.get(i).getUnit());

		}

    	//print everything from items remaining array
    	System.out.println("Items Remaining:");
    	for(int i = 0; i < itemsRemaining.size(); i++) {

			System.out.println ("Slot " + (i + 1) + " contains item: " + itemsRemaining.get(i).getItemName() + " priority: " + itemsRemaining.get(i).getPriority() + " price: " + itemsRemaining.get(i).getPrice() + " Quantity: " + itemsRemaining.get(i).getQuantity() + " " + itemsRemaining.get(i).getUnit());

		}
    	System.out.println("Bank Account Post Shopping: " + Double.parseDouble(df2.format(Double.parseDouble(shoppingCart.getBankAccount()))));*/

    }

    @Override
    public void writeItemsToFile() {
        DecimalFormat df2 = new DecimalFormat("#.##");

        try {
            FileWriter fileWriter = new FileWriter(this.fileNameWrite);

            fileWriter.write(shoppingCart.getUserName() + "'s Shopping Cart\n");

            fileWriter.write("You have $" + getBankAccountInitial() + " in the bank." + "\n");

            fileWriter.write("Item List:" + "\n");

            writeShoppingCartToFile(shoppingCart, fileWriter);
    		/*for(int i = 0; i < shoppingCart.getItemListSorted().size(); i++) {


    			fileWriter.write ("Slot " + (i + 1) + " contains item: " + shoppingCart.getItemListSorted().get(i).getItemName() + " priority: " + shoppingCart.getItemListSorted().get(i).getPriority() + " price: " + shoppingCart.getItemListSorted().get(i).getPrice() + " quantity: " + shoppingCart.getItemListSorted().get(i).getQuantity() + " " + shoppingCart.getItemListSorted().get(i).getUnit() + "\n");

    		}*/

            fileWriter.write("Items Bought:" + "\n");

            writeItemClassArrayToFile(itemsBought, fileWriter);
	    	/*for(int i = 0; i < itemsBought.size(); i++) {
	    		fileWriter.write ("Slot " + (i + 1) + " contains item: " + itemsBought.get(i).getItemName() + " priority: " + itemsBought.get(i).getPriority() + " price: " + itemsBought.get(i).getPrice() + " quantity: " + itemsBought.get(i).getQuantity() + " " + itemsBought.get(i).getUnit() + "\n");


    		}*/

            //print everything from items remaining array
            fileWriter.write("Items Remaining:" + "\n");

            writeItemClassArrayToFile(itemsRemaining, fileWriter);

	    	/*for(int i = 0; i < itemsRemaining.size(); i++) {
	    		fileWriter.write ("Slot " + (i + 1) + " contains item: " + itemsRemaining.get(i).getItemName() + " priority: " + itemsRemaining.get(i).getPriority() + " price: " + itemsRemaining.get(i).getPrice() + " quantity: " + itemsRemaining.get(i).getQuantity() + " " + itemsRemaining.get(i).getUnit() + "\n");

    		}*/
            fileWriter.write("Bank Account Post-Shopping: " + Double.parseDouble(df2.format(Double.parseDouble(shoppingCart.getBankAccount()))));
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    @Override
    public void printItemClassArrayToConsole(ArrayList<ItemClass> itemList) {

        //you were working here
        for(int i = 0; i < itemList.size(); i++) {

            System.out.println ("Slot " + (i + 1) + " contains item: " + itemList.get(i).getItemName() + " priority: " + itemList.get(i).getPriority() + " price: " + itemList.get(i).getPrice() + " Quantity: " + itemList.get(i).getQuantity() + " " + itemList.get(i).getUnit());

        }
    }


    public void printShoppingCartToConsole(ShoppingCart shoppingCart) {
        for(int i = 0; i < shoppingCart.getItemListSorted().size(); i++) {

            System.out.println ("Slot " + (i + 1) + " contains item: " + shoppingCart.getItemListSorted().get(i).getItemName() + " priority: " + shoppingCart.getItemListSorted().get(i).getPriority() + " price: " + shoppingCart.getItemListSorted().get(i).getPrice() + " Quantity: " + shoppingCart.getItemListSorted().get(i).getQuantity() + " " + shoppingCart.getItemListSorted().get(i).getUnit());

        }

    }
    public void writeItemClassArrayToFile(ArrayList<ItemClass> itemList, FileWriter fileWriter) {

        try {
            //fileWriter = new FileWriter(fileWrite);
            for(int i = 0; i < itemList.size(); i++) {
                fileWriter.write ("Slot " + (i + 1) + " contains item: " + itemList.get(i).getItemName() + " priority: " + itemList.get(i).getPriority() + " price: " + itemList.get(i).getPrice() + " quantity: " + itemList.get(i).getQuantity() + " " + itemList.get(i).getUnit() + "\n");
                //System.out.println("Slot " + (i + 1) + " contains item: " + itemList.get(i).getItemName() + " priority: " + itemList.get(i).getPriority() + " price: " + itemList.get(i).getPrice() + " quantity: " + itemList.get(i).getQuantity() + " " + itemList.get(i).getUnit());

                fileWriter.flush();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void writeShoppingCartToFile(ShoppingCart shoppingCart, FileWriter fileWriter) {

        try {

            for(int i = 0; i < shoppingCart.getItemListSorted().size(); i++) {
                fileWriter.write ("Slot " + (i + 1) + " contains item: " + shoppingCart.getItemListSorted().get(i).getItemName() + " priority: " + shoppingCart.getItemListSorted().get(i).getPriority() + " price: " + shoppingCart.getItemListSorted().get(i).getPrice() + " quantity: " + shoppingCart.getItemListSorted().get(i).getQuantity() + " " + shoppingCart.getItemListSorted().get(i).getUnit() + "\n");
                //System.out.println ("Slot " + (i + 1) + " contains item: " + shoppingCart.getItemListSorted().get(i).getItemName() + " priority: " + shoppingCart.getItemListSorted().get(i).getPriority() + " price: " + shoppingCart.getItemListSorted().get(i).getPrice() + " quantity: " + shoppingCart.getItemListSorted().get(i).getQuantity() + " " + shoppingCart.getItemListSorted().get(i).getUnit());

                fileWriter.flush();

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public void setItemsBought(ArrayList<ItemClass> itemsBought) {
        this.itemsBought = itemsBought;

    }

    public ArrayList<ItemClass> getItemsBought() {

        return itemsBought;
    }


    public void setItemsRemaining(ArrayList<ItemClass> itemsRemaining) {
        this.itemsRemaining = itemsRemaining;

    }

    public ArrayList<ItemClass> getItemsRemaining() {

        return itemsRemaining;
    }
    public void setBankAccountInitial(String bankAccountInitial) {
        this.bankAccountInitial = bankAccountInitial;

    }

    public String getBankAccountInitial() {

        return bankAccountInitial;
    }

    public void subtractBankAccount(String price) {

        shoppingCart.setBankAccount(String.valueOf(Double.parseDouble(shoppingCart.getBankAccount()) - Double.parseDouble(price)));
        //this.bankAccount = String.valueOf(Double.parseDouble(bankAccount) - price;

    }
    public boolean equalsMethod(ArrayList<ItemClass> itemList, String line) {

        boolean isDuplicate = false;

        for(int z = 0; z < itemList.size(); z++) {
			/*System.out.println ("Iteration " + z);
			System.out.println ("Previous Item Name " + itemList[z].getItemName());
			System.out.println ("New Item Name " + line);*/
            if(itemList.get(z).getItemName().equals(line)){

                isDuplicate = true;
            }


        }
        return isDuplicate;
    }


}