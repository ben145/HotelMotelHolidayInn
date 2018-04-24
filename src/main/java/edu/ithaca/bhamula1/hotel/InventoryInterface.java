package edu.ithaca.bhamula1.hotel;

public interface InventoryInterface {
    //getters
    String getItem();
    int getQuantity();

    //setters
    void setItem(String itemName);
    void setQuantity(int quantity);

    public void addItemToInventory();
    public String toString_Inventory();
}
