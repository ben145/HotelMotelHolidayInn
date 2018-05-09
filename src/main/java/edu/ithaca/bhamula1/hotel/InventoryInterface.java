package edu.ithaca.bhamula1.hotel;

public interface InventoryInterface {

    String getItem();

    void setItem(String itemName);

    int getQuantity();

    void setQuantity(int quantity);

    /**
     * Takes in input for item to be added to inventory, namely the name of
     *  the item and how many/much of it is to be added
     */
    public void addItemToInventory();

    public String toString_Inventory();
}
