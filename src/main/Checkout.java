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

    Checkout(){
        this._item = new ArrayList<Items>();
        this.totalAmountPayable = 0;
        this.discount = 0;
        this.totalDiscount = 0;
        this.totalAmount = 0;
    }

    Checkout(List<IPricingRule> pricingRules) {
        this._pricingRules = pricingRules;
        this._item = new ArrayList<Items>();
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
            ListIterator<Items> listIterator = this._item.listIterator();
            while (listIterator.hasNext()) {
                Items items = listIterator.next();
                if (items.getItemCode().equals(i.getItemCode())) {
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
        BigDecimal totalDiscount = BigDecimal.valueOf(0); //Initialize the total discount of the purchase
        // Sum up the values of the individual items on the list
        //BigDecimal totalAmount = this._item.Sum(() => {  }, x.Price);
        BigDecimal totalAmount = BigDecimal.valueOf(0);

        /*HashMap<String, Items> map = new HashMap();
        for (Items _i : _item) {
            String ItemCode = _i.getItemCode();
            ProfitAndLossDataDO sum = map.get(name);
            if (sum == null) {
                sum = new ProfitAndLossDataDO(name, 0.0);
                map.put(name, sum);
            }
            sum.setLedgerAmount(sum.getLedgerAmount() + p.getLedgerAmount());
        }*/

        // Apply discounts based on the predefined pricing rules
        for (IPricingRule pricingRule : this._pricingRules) {
            //For each item, if there's a discount, calculate and append
            totalDiscount = (totalDiscount.add(pricingRule.GetDiscount(this._item)));
        }
        return (totalAmount.subtract(totalDiscount));
    }
}
