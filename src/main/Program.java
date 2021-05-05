package main;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        //Create the products
        Items MUG = new Items("MUG","Triggerise Mug",BigDecimal.valueOf(4.00), 1000, true);
        Items TSHIRT = new Items("TSHIRT","Triggerise Tshirt", BigDecimal.valueOf(21.00), 1000, true);
        Items USBKEY = new Items("USBKEY","Triggerise USB Key", BigDecimal.valueOf(10.00), 1000);

        //Create Object to add the various pricing rules if any
        //New rules are added by inheriting the IPricingRule Interface
        //ArrayList<IPricingRule> pricingRules = new ArrayList<IPricingRule>();
        var pricingRules = new ArrayList<IPricingRule>();
        pricingRules.add(new Billing.NoDiscountRule());
        pricingRules.add(new Billing.TShirtRule());
        pricingRules.add(new Billing.MugsRule());

        //Proceed to Checkout Process
        //Checkout checkout = new Checkout(pricingRules);
        var checkout = new Checkout(pricingRules);
        checkout.scan(MUG);
        checkout.scan(TSHIRT);
        checkout.scan(MUG);
        checkout.scan(MUG);
        checkout.scan(USBKEY);
        checkout.scan(TSHIRT);
        checkout.scan(TSHIRT);
        System.out.println(("Total Amount: " + checkout.Total()));
    }
}
