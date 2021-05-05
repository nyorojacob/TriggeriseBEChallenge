package main;

import java.math.BigDecimal;
import java.util.*;

public class Checkout {
    ArrayList<Items> _item;
    double totalAmountPayable;
    double discount;
    double totalDiscount;
    double totalAmount;

    private List<IPricingRule> _pricingRules;

/*    Checkout(){
        this._item = new ArrayList<Items>();
        this.totalAmountPayable = 0;
        this.discount = 0;
        this.totalDiscount = 0;
        this.totalAmount = 0;
    }*/

    Checkout(List<IPricingRule> pricingRules) {
        this._pricingRules = pricingRules;
        this._item = new ArrayList<>();
        this.totalAmountPayable = 0;
        this.discount = 0;
        this.totalDiscount = 0;
        this.totalAmount = 0;
    }

    //public void addItem(Items item){
    public void scan(Items item){
        this._item.add(item);
    }

    public void removeItem(Items i){
        if(this._item.size()>=1) {
            //ListIterator<Items> listIterator = this._item.listIterator();
            /*while (listIterator.hasNext()) {
                Items items = listIterator.next();
                if (items.getItemCode().equals(i.getItemCode())) {
                    this._item.remove(i);
                    break;
                }
            }*/
            for(Items item: _item)
            {
                if (item.getItemCode().equals(i.getItemCode())) {
                    this._item.remove(i);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Item doesn't exist");
        }
    }

    public final BigDecimal Total() {
        BigDecimal totalAmount = BigDecimal.valueOf(0); //Initialize the total amount
        //Using the different pricing rules, all prices will be calculated based on the pricing rules its customized for
        for (IPricingRule pricingRule : this._pricingRules) {
            //Calculate and append the price for each item
            totalAmount = (totalAmount.add(pricingRule.GetDiscount(this._item)));
        }
        return totalAmount;
    }
}
