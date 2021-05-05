package main;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

public class Billing {

    BigDecimal discount;


    public BigDecimal getDiscount() {
        return discount;
    }

    public static class DiscountsRule implements IPricingRule {
        public final BigDecimal GetDiscount(List<Items> cart) {

            BigDecimal TotalItemCost = BigDecimal.valueOf(0);
            BigDecimal TShirtTotalCost = BigDecimal.valueOf(0);
            BigDecimal MugsTotalCost = BigDecimal.valueOf(0);
            int totalNumberOfTShirtsInCart = 0;
            int totalNumberOfMugsInCart = 0;
            int totalNumberOfUSBKeysInCart = 0;
            BigDecimal USBKeyTotalCost = BigDecimal.valueOf(0);


            BigDecimal TShirtDiscountedRate = BigDecimal.valueOf(0.3); //Discount is 30% of total price if more than 3 T-Shirts
            BigDecimal MugsDiscountedRate = BigDecimal.valueOf(0.5); //Half price (2 for 1); Then we'll round of

            ListIterator<Items> listIterator = cart.listIterator();
            while (listIterator.hasNext()) {
                Items items = listIterator.next();
                if (items.getItemCode().equals("TSHIRT")) {
                    totalNumberOfTShirtsInCart++;
                }
                else if (items.getItemCode().equals("MUG")) {
                    totalNumberOfMugsInCart++;
                }
                else if (items.getItemCode().equals("USBKEY")) {
                    totalNumberOfUSBKeysInCart++;
                    USBKeyTotalCost = USBKeyTotalCost.add(items.getItemUnitPrice());
                }
            }


            if ((totalNumberOfTShirtsInCart >= 3)) {
                TShirtTotalCost = BigDecimal.valueOf(totalNumberOfTShirtsInCart).multiply(TShirtDiscountedRate);
            }
            else {

            }


            TotalItemCost = TShirtTotalCost.add(MugsTotalCost).add(USBKeyTotalCost);
            return TotalItemCost;
        }
    }

}
