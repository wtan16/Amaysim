import java.math.BigDecimal;

public class Product {

    private String code;
    private String name;
    private BigDecimal price;
    
    public Product(){   	
    }
 
    public Product(String code, String name, BigDecimal price){
    	this.code =  code;
    	this.name =  name;
    	this.price =  price;
    }
 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    
}

