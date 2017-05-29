import java.math.BigDecimal;

public class BulkDiscountPromo extends Promos{

	
	@Override
	public BigDecimal calcDiscount(int qty, BigDecimal origPrice ) {
		BigDecimal totDiscount = new BigDecimal(0);
		BigDecimal newPrice = new BigDecimal(0);
		BigDecimal largePromoPrice = new BigDecimal(GlobalConstants.LARGE_PROMO_PRICE);
		
	    if (qty > 3) {
		    newPrice =  origPrice.subtract(largePromoPrice);
	    	totDiscount = newPrice.multiply(origPrice);
		}
  		    
	 	return totDiscount;
		
	}
	
	
	
	
	
}
