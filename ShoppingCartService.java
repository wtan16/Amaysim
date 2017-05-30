import java.math.BigDecimal;
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
		BigDecimal totCost=new BigDecimal(0),totDiscount=new BigDecimal(0);
		Product prod=null;
		Boolean isPromo = false;
		Promos promo = null;
	  
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
	    	    cart.add( new Item(1,GlobalConstants.ONE_GB,"") );
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
		
		for (Item itm : cartList) {
		    prod = prodSvc.findByCode(itm.getProductCode());
	
			System.out.println("\nId:" + itm.getId() + "  Product: " + prod.getName()  + "  Qty: " + itm.getQty() + "  Price: " + prod.getPrice() );
		}
		System.out.println("============================================================================" );		
		System.out.println("\nTotal Cost:" + cart.getTotalCost() + "\n\n\n");
	
	}


	
	public static void main(String[] arg) {
		//Create dummy items for testing
		Item item = null;;
		List<Item> itemList  = new ArrayList<Item>();
		
		item =  new Item(1,"ult_small","");		
		itemList.add(item);		
		item =  new Item(2,"ult_medium","");		
		itemList.add(item);	
		item =  new Item(3,"ult_large","");		
		itemList.add(item);
		item =  new Item(3,"1gb","I<3AMAYSIM");		
		itemList.add(item);
		
		ShoppingCartService cartService = new ShoppingCartService(itemList);
		cartService.processOrder();
		cartService.displayCart();
		     
		
	}

	
}
