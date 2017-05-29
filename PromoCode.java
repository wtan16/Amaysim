
import java.math.BigDecimal;

public class PromoCode extends Promos{

	
	@Override
	public BigDecimal calcDiscount(int qty, BigDecimal price ) {
		BigDecimal totDiscount = new BigDecimal(0);
		BigDecimal totCost = new BigDecimal(0);
		
    
		int smallCount = qty/3;
		
  	    if (smallCount > 0) {
   		 	totDiscount = price.multiply(new BigDecimal(smallCount));
  	    }
  	    
	 	return totDiscount;
		
	}
	
	

}
