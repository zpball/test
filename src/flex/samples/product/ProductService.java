package flex.samples.product;

import java.util.List;

public interface ProductService {

	public List<Product> getProducts();

	public List<Product> getProductsByName(String name);

	public Product getProduct(int productId);

	public Product create(Product product);

	public boolean update(Product product);

	public boolean remove(Product product);

	public boolean delete(Product product);

}