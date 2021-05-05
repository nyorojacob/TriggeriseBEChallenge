package main;

import java.math.BigDecimal;
import java.util.List;

public interface IPricingRule {
    BigDecimal GetDiscount(List<Items> cart);
}

