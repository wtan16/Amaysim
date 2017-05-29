import java.math.BigDecimal;

public class ThreeForTwoPromo extends Promos{

	
	@Override
	public BigDecimal calcDiscount(int qty, BigDecimal price ) {
		BigDecimal totDiscount = new BigDecimal(0);
		int smallCount = qty/3;
		
  	    if (smallCount > 0) {
   		 	totDiscount = price.multiply(new BigDecimal(smallCount));
  	    }
  	    
	 	return totDiscount;
		
	}
	
	

}
