package edu.ithaca.bhamula1.hotel;

public interface InventoryInterface {

    String getItem();

    void setItem(String itemName);

    int getQuantity();

    void setQuantity(int quantity);

    public void addItemToInventory();

    public String toString_Inventory();
}
