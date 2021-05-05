package main;

import java.math.BigDecimal;

public class Items{
    String itemCode;
    String itemName;
    BigDecimal itemUnitPrice;
    int itemQuantity;
    Boolean hasOffer = false;

    public Items(String itemCode, String itemName, BigDecimal itemUnitPrice, int itemQuantity, Boolean hasOffer)
    {
        this.itemCode = itemCode; //Code to distinguish the product
        this.itemName = itemName; //The product name
        this.itemUnitPrice = itemUnitPrice; //The default price for the product
        this.itemQuantity = itemQuantity;   //The current stock quantity of the product
        this.hasOffer = hasOffer;
    }

    //Overload method incase the offer value isn't provided for a product
    public Items(String itemCode, String itemName, BigDecimal itemUnitPrice, int itemQuantity)
    {
        this.itemCode = itemCode; //Code to distinguish the product
        this.itemName = itemName; //The product name
        this.itemUnitPrice = itemUnitPrice; //The default price for the product
        this.itemQuantity = itemQuantity;   //The current stock quantity of the product
        this.hasOffer = false;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCode(){
        return this.itemCode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName(){
        return this.itemName;
    }

    public void setItemUnitPrice(BigDecimal itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public BigDecimal getItemUnitPrice(){
        return this.itemUnitPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemQuantity(){
        return this.itemQuantity;
    }
}
