import java.util.List;

public interface ProductDAO {
	    List<Product> findAll();
	    Product findByCode(String code);
	    Product findByName(String name);
	    boolean insertProduct(Product product);
	    boolean updateProduct(Product product);
	    boolean deleteProduct(Product product);

}

