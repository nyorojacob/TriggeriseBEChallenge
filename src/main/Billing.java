package main;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Billing {
    public static class NoDiscountRule implements IPricingRule {
        public BigDecimal GetDiscount(List<Items> cart) {
            int totalNumberOfItemsInCart = 0;
            BigDecimal ItemsTotalCost = BigDecimal.valueOf(0);
            //Use predicates to filter the list
            Predicate<Items> hasOffer = c -> c.getHasOffer().equals(true);

            /*
            List<Items> NonOfferItems = cart
                    .stream()
                    //.filter(c -> c.getHasOffer().equals(false))
                    .filter(hasOffer.negate())
                    .map(c-> new Items(c.getItemCode(), c.getItemName(), c.getItemUnitPrice(), c.getItemQuantity(), c.getHasOffer()))
                    .collect(Collectors.toList());
            totalNumberOfItemsInCart = NonOfferItems.size(); //No of TShirts
            //Calculate total cost for the remaining items
            ItemsTotalCost = BigDecimal.valueOf(totalNumberOfItemsInCart).multiply(NonOfferItems.get(0).getItemUnitPrice());
            */

            //Fetching NonOfferItems data as a Set
            Set<Items> NonOfferItems = cart
                    .stream()
                    //.filter(c -> c.getHasOffer().equals(false))
                    .filter(hasOffer.negate())
                    .map(c-> new Items(c.getItemCode(), c.getItemName(), c.getItemUnitPrice(), c.getItemQuantity(), c.getHasOffer()))
                    .collect(Collectors.toSet());
            //Iterating Set
            for(Items noi: NonOfferItems) {
                //Add each items price individually (as they may be unique, and not just the three previously described)
                ItemsTotalCost = ItemsTotalCost.add(noi.getItemUnitPrice());
            }

            return ItemsTotalCost;
        }
    }

    public static class TShirtRule implements IPricingRule {
        public BigDecimal GetDiscount(List<Items> cart) {
            int totalNumberOfTShirtsInCart;
            BigDecimal TShirtUnitPrice = BigDecimal.valueOf(0);
            BigDecimal TShirtTotalCost = BigDecimal.valueOf(0);
            BigDecimal TShirtDiscountedRate = BigDecimal.valueOf(0.7); //Discount is 30% of total price if more than 3 T-Shirts

            //Use predicates to filter the list
            Predicate<Items> hasOffer = c -> c.getHasOffer().equals(true);
            Predicate<Items> isTShirt = c -> c.getItemCode().equals("TSHIRT");
            //List with tshirts only
            List<Items> TShirts = cart
                    .stream()
                    //.filter(c -> c.getItemCode().equals("TSHIRT"))
                    .filter(hasOffer.and(isTShirt))
                    .collect(Collectors.toList());
            totalNumberOfTShirtsInCart = TShirts.size(); //No of TShirts
            //Apply formula based on the quantity, as long as there at items in the cart
            if(totalNumberOfTShirtsInCart > 0)
            {
                //As Items are the same, Get value of first element in list and multiply by the item price
                TShirtUnitPrice = TShirts.get(0).getItemUnitPrice();
                if(totalNumberOfTShirtsInCart < 3) {
                    TShirtTotalCost = BigDecimal.valueOf(totalNumberOfTShirtsInCart).multiply(TShirtUnitPrice);
                }
                else{
                    //compunded by the discount
                    TShirtTotalCost = BigDecimal.valueOf(totalNumberOfTShirtsInCart).multiply(TShirtUnitPrice).multiply(TShirtDiscountedRate);
                }
            }
            return TShirtTotalCost;
        }
    }

    public static class MugsRule implements IPricingRule {
        public BigDecimal GetDiscount(List<Items> cart) {
            int totalNumberOfMugsInCart = 0;
            int totalDiscountedMugsInCart = 0;
            BigDecimal MugUnitPrice = BigDecimal.valueOf(0);
            BigDecimal MugsTotalCost = BigDecimal.valueOf(0);
            //Use predicates to filter the list
            Predicate<Items> hasOffer = c -> c.getHasOffer().equals(true);
            Predicate<Items> isMug = c -> c.getItemCode().equals("MUG");
            //List with mugs only
            List<Items> Mugs = cart
                    .stream()
                    //.filter(c -> c.getItemCode().equals("MUG"))
                    .filter(hasOffer.and(isMug))
                    .collect(Collectors.toList());
            totalNumberOfMugsInCart = Mugs.size(); //No of Mugs
            //Apply formula based on the quantity, as long as there at items in the cart
            if (totalNumberOfMugsInCart > 0) {
                //As Items are the same, Get value of first element in list and multiply by the item price
                MugUnitPrice = Mugs.get(0).getItemUnitPrice();
                //We are implementing 2 for 1 promotion; so we'll use Math.ceil to round off division by 2 to next full number
                //We need to first cast the int as a double to ensure the resultant division doesn't result in an int
                double DblDiscountedMugsInCart = totalNumberOfMugsInCart / (double) 2;
                totalDiscountedMugsInCart = (int) Math.ceil(DblDiscountedMugsInCart);
                MugsTotalCost = BigDecimal.valueOf(totalDiscountedMugsInCart).multiply(MugUnitPrice);
            }
            return MugsTotalCost;
        }
    }
}
