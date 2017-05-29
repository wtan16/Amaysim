import java.math.BigDecimal;

public abstract class Promos {
	
	public abstract BigDecimal calcDiscount(int qty, BigDecimal price) ;
	
	public BigDecimal fixedDiscount(BigDecimal price, BigDecimal percent) {
		BigDecimal totDiscount = new BigDecimal(0);
		
     	totDiscount = price.multiply(percent);
    	return totDiscount;
		
	}
	
	 
}
