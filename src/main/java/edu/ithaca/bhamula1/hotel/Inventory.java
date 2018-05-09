package edu.ithaca.bhamula1.hotel;

import java.util.Scanner;


public class Inventory implements InventoryInterface {
    private String item;
    private int quantity;

    // Default constructor
    public Inventory(){
        this.item = "";
        this.quantity = -1;
    }

    /**
     * Inventory constructor
     * @param item      Name of item
     * @param quantity  Quantity of said item
     */
    public Inventory(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {return item;}

    public void setItem(String itemName) {this.item = itemName;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void addItemToInventory() {
        System.out.println("Enter new item name: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        this.item = input;
        System.out.println("Enter item quantity: ");
        input = scanner.nextLine();
        this.quantity = Integer.parseInt(input);
    }

    public String toString_Inventory() {return (item + ", " + quantity);}
}
