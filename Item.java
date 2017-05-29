import java.math.BigDecimal;
import java.util.ArrayList;

public class Item {

	private int id=0;
	private int qty;
    private String productCode;
    private String promoCode;
    
    public Item(){
    	this.id = id++;
    }
    
    public Item ( int qty, String productCode, String promoCode) {
    	this.id = id++;
    	this.qty = qty;
    	this.productCode =  productCode;
    	this.promoCode = promoCode;
     	
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Item)) return false;
	    Item o = (Item) obj;
	    return o.id == this.id;
	}

    
	
}