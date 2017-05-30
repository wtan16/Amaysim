import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;;


public class ProductServiceImpl implements ProductService {

    private DataSource dataSource;

    public ProductServiceImpl() {
    	/* Uncomment if reading from a real database
        try {
            dataSource = getDataSource();
        } catch (DAOException dx) {
            dx.printStackTrace();
        }
        */
    }
 
    /* (non-Javadoc)
	 * @see ProductService#findByCode(java.lang.String)
	 */
    @Override
	public Product findByCode(String code) {

    	ProductDAOImpl amaysimProductDAO = null;
    	
    	/* Uncomment if reading from a real database
        try {
        	amaysimProductDAO = getProductDAO();
        } catch (DAOException dx) {
            dx.printStackTrace();
        } finally {
            close(amaysimProductDAO);
        }
        */
     
        
    	amaysimProductDAO = new ProductDAOImpl();

        return amaysimProductDAO.findByCode(code);
    }

    
    /* (non-Javadoc)
	 * @see ProductService#findByCode(java.lang.String)
	 */
    @Override
	public List<Product> findAll() {

    	ProductDAOImpl amaysimProductDAO = null;

    	/* Uncomment if reading from a real database
        try {
        	amaysimProductDAO = getProductDAO();
        } catch (DAOException dx) {
            dx.printStackTrace();
        } finally {
            close(amaysimProductDAO);
        }
        */
    	amaysimProductDAO = new ProductDAOImpl();

        return amaysimProductDAO.findAll();

    }

    
    /* (non-Javadoc)
	 * @see ProductService#update(Product)
	 */
    @Override
	public void update(Product product) {

    	ProductDAOImpl amaysimProductDAO = null;

        try {
            amaysimProductDAO = getProductDAO();

            amaysimProductDAO.updateProduct(product);
        } catch (DAOException dx) {
            dx.printStackTrace();
        } finally {
            close(amaysimProductDAO);
        }
    }

    private ProductDAOImpl getProductDAO() throws DAOException {
        try {
            Connection conn = dataSource.getConnection();
            return new ProductDAOImpl(conn);
        } catch (SQLException sx) {
            throw new DAOException(sx);
        }
    }

    // TODO move this to an abstract base class
    private DataSource getDataSource() throws DAOException {
        InitialContext ctx = null;

        try {
            ctx = new InitialContext();
        } catch (NamingException nx) {
            throw new DAOException("Failed to create initial context");
        }

        if (ctx == null) {
            throw new DAOException("Failed to create initial context");
        }

        DataSource ds = null;
        try {
            // TODO get this name from global context
            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc");
        } catch (NamingException nx) {
            throw new DAOException("Failed to retrieve datasource");
        }

        if (ds == null) {
            throw new DAOException("Data source not found!");
        }

        return ds;
    }      

    private void close(ProductDAOImpl amaysimProductDAO) {
       if (amaysimProductDAO == null) {
            return;
        }

        amaysimProductDAO.closeConnection();
    }
}
