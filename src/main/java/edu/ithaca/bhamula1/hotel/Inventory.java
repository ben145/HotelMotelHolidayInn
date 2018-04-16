package edu.ithaca.bhamula1.hotel;

import java.util.Scanner;

/**
 *
 */
public class Inventory implements InventoryInterface {
    String item;
    int quantity;

    //empty inventory item
    public Inventory(){
        this.item = "";
        this.quantity = -1;
    }

    public Inventory(String item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    //getters
    public String getItem(){
        return item;
    }
    public int getQuantity(){
        return quantity;
    }

    //setters
    public void setItem(String itemName){
        this.item = itemName;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void addItemToInventory(){
        System.out.println("Enter new item name: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        this.item = input;
        System.out.println("Enter item quantity: ");
        input = scanner.nextLine();
        this.quantity = Integer.parseInt(input);
    }

    public String toString_Inventory(){
        String inventoryItem = item + ", " + quantity;
        return inventoryItem;
    }
}
