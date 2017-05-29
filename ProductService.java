import java.util.List;

public interface ProductService {
    List<Product> findAll();
		 
	Product findByCode(String code);

	void update(Product product);

}