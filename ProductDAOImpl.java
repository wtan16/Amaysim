import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
	Connection conn = null;

	public ProductDAOImpl(Connection conn) {
		this.conn = conn;
	}

	
	@Override
	public List<Product> findAll() {
		Product prod = null;
		
		// Created dummy records for testing
		List<Product> prodList = new ArrayList<Product>();
		prod =  new Product("ult_small","Unlimited 1GB",new BigDecimal(24.90));		
		prodList.add(prod);
		prod =  new Product("ult_medium","Unlimited 2GB",new BigDecimal(29.90));		
		prodList.add(prod);
		prod =  new Product("ult_large","Unlimited 5GB",new BigDecimal(44.90));		
		prodList.add(prod);
		prod =  new Product("1gb","1 GB Data-pack",new BigDecimal(9.90));		
		prodList.add(prod);
	     
		return prodList;
		
		
	}

	@Override
	public Product findByCode(String code) {
		// TODO Auto-generated method stub
		return new Product();
	}

	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return new Product();
	}

	@Override
	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		return true;
	}

	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}
	
}
