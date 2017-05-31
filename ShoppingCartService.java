import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService {
	
	List<Product> products;
	List<Item> itemList ;
	int ultSmallCount,ultMedium,ultLarge,oneGb=0;
	ShoppingCart cart = new ShoppingCart();
	ProductServiceImpl prodSvc = new ProductServiceImpl(); 
	
	public ShoppingCartService (List<Item> items) {
		products = prodSvc.findAll();
		this.itemList = items;
	}
	
	
	public void processOrder() {
		BigDecimal totCost  =new BigDecimal(0),totDiscount=new BigDecimal(0);
		Product prod=null;
		Boolean isPromo = false;
		Promos promo = null;
		BigDecimal prodPrice = new BigDecimal(0);
		
		for(Item item: itemList){
		    prod = prodSvc.findByCode(item.getProductCode());
			  	 
	        switch(item.getProductCode()){  
		        case GlobalConstants.ULT_SMALL: ultSmallCount = ultSmallCount + item.getQty();break;  
		        case GlobalConstants.ULT_MEDIUM: ultMedium = ultMedium + item.getQty();break;  
		        case GlobalConstants.ULT_LARGE: ultLarge = ultLarge + item.getQty();break;  
		        case GlobalConstants.ONE_GB: oneGb = oneGb + item.getQty();
		        	if(GlobalConstants.ULT_MEDIUM.equalsIgnoreCase(item.getProductCode())){
		        		oneGb += 1;
		        	}
		    	    break;  
		        default:System.out.println("Product not found");  
	        }
	        
	        if (GlobalConstants.DISCOUNT_PROMO.equalsIgnoreCase(item.getPromoCode())) {
	        	isPromo = true;
	        }
	        
	        cart.add(item);
	        
	        totCost =  totCost.add(prod.getPrice());
	    }
		  
		 
	    if (ultSmallCount > 0) {      	
			prod = prodSvc.findByCode(GlobalConstants.ULT_SMALL);
			promo =  new ThreeForTwoPromo();
			totDiscount = promo.calcDiscount(ultSmallCount, prod.getPrice());
	    	totCost = totCost.subtract(totDiscount);
	    }
	    
	      
	    if (ultMedium > 0) {      	
	    	for (int i=0; i <= ultMedium; i++) {
	    	    cart.add( new Item(GlobalConstants.ONE_GB,1,new BigDecimal(0),"") );
	    	}
	    }
	    	   
	    if (ultLarge > 3)  {      	
	    	prod = prodSvc.findByCode(GlobalConstants.ULT_LARGE);
			promo =  new BulkDiscountPromo();
			totDiscount = promo.calcDiscount(ultLarge, prod.getPrice());
	    	totCost = totCost.subtract(totDiscount);	    	     	
	    }
	    
    	if (isPromo) {
    		totCost = totCost.subtract(totDiscount);
    	}
   	
	    cart.setTotalCost(totCost);
	}

	

	public void displayCart() {
		Product prod=null;		
		List<Item> cartList = cart.getItems();  ;
		System.out.println("\n\n\n\n\nCustomer XYZ Checkout: \n" );	
		DecimalFormat df = new DecimalFormat("#,#00.00");
		System.out.println("\nId       " + "Product          "   + "Qty     "  + "Price    \n"  );

		for (Item itm : cartList) {
		    prod = prodSvc.findByCode(itm.getProductCode());
		    
			System.out.println( itm.getId() + "      " + prod.getName()  + "       " + itm.getQty() + "      " + df.format(itm.getPrice()) );
		}
		System.out.println("====================================================" );		
		System.out.println("\n                       Total:   " + df.format(cart.getTotalCost()) + "\n\n\n");
	
	}


	
	public static void main(String[] arg) {
		//Create dummy items for testing
		Item item = null;;
		List<Item> itemList  = new ArrayList<Item>();
		
		item =  new Item(GlobalConstants.ULT_SMALL,1,new BigDecimal(24.90),"");		
		itemList.add(item);		
		item =  new Item(GlobalConstants.ULT_MEDIUM,2,new BigDecimal(29.90),"");			
		itemList.add(item);	
      		item =  new Item(GlobalConstants.ULT_LARGE,3,new BigDecimal(44.90),"");				
		itemList.add(item);
		item =  new Item(GlobalConstants.ONE_GB,1,new BigDecimal(9.90),"");					
		itemList.add(item);
		
		ShoppingCartService cartService = new ShoppingCartService(itemList);
		cartService.processOrder();
		cartService.displayCart();
		     
		
	}

	
}
