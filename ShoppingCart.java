import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

List<Item> cartList;
BigDecimal totalCost;

public ShoppingCart(){
	cartList = new ArrayList<Item>();
}


public BigDecimal getTotalCost() {
	return totalCost;
}


public void setTotalCost(BigDecimal totalCost) {
	this.totalCost = totalCost;
}


public void add(Item item) {
	cartList.add(item);
}

public void remove(Item item) {
	cartList.remove(item);
}

public int getTotalItems() {
    return cartList.size();
}


public List<Item> getItems() {
    return cartList;
}    



}