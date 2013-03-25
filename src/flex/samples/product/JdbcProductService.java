package flex.samples.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcProductService implements ProductService {
	
	private final SimpleJdbcTemplate template;
	
	private final ParameterizedRowMapper<Product> productMapper = new ParameterizedRowMapper<Product>(){

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Product(rs.getInt("product_id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getString("image"), 
					rs.getString("category"), 
					rs.getDouble("price"),
					rs.getInt("qty_in_stock"));
		}
	};
	
	public JdbcProductService(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
	}
	
	public Product getProduct(int productId) {
		return template.queryForObject("SELECT * FROM product WHERE product_id=?", productMapper, productId);
	}

	public List<Product> getProducts() {
		return template.query("SELECT * FROM product ORDER BY name", productMapper);
	}

	public List<Product> getProductsByName(String name) {
		return template.query("SELECT * FROM product WHERE UPPER(name) LIKE ? ORDER BY name", productMapper, name);
	}
	
	public Product create(Product product) {
		template.update("INSERT INTO product (name, description, image, category, price, qty_in_stock) VALUES (?, ?, ?, ?, ?, ?)", 
				product.getName(),
				product.getDescription(),
				product.getImage(),
				product.getCategory(),
				product.getPrice(),
				product.getQtyInStock());
		
		int productId = template.queryForInt("CALL IDENTITY()");
		product.setProductId(productId);
		
		return product;
	}
	
	public boolean update(Product product) {
		int count = template.update("UPDATE product SET name=?, description=?, image=?, category=?, price=?, qty_in_stock=? WHERE product_id=?", 
				product.getName(),
				product.getDescription(),
				product.getImage(),
				product.getCategory(),
				product.getPrice(),
				product.getQtyInStock(),
				product.getProductId());
		return (count == 1);
	}

	public boolean remove(Product product) {
		int count = template.update("DELETE FROM product WHERE product_id=?", product.getProductId());
		return (count == 1);
	}
	
	public boolean delete(Product product) {
		return remove(product);
	}
}
