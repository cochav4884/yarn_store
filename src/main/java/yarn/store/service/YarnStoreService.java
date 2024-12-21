package yarn.store.service;


import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yarn.store.controller.model.YarnStoreData;
import yarn.store.controller.model.YarnStoreData.CartData;
import yarn.store.controller.model.YarnStoreData.CartItemData;
import yarn.store.controller.model.YarnStoreData.CustomerData;
import yarn.store.controller.model.YarnStoreData.ProductData;
import yarn.store.dao.CartDao;
import yarn.store.dao.CartItemDao;
import yarn.store.dao.CustomerDao;
import yarn.store.dao.ProductDao;
import yarn.store.dao.YarnStoreDao;
import yarn.store.entity.Cart;
import yarn.store.entity.CartItem;
import yarn.store.entity.Customer;
import yarn.store.entity.Product;
import yarn.store.entity.YarnStore;


@Service
public class YarnStoreService {
	@Autowired
	private YarnStoreDao yarnStoreDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private CartItemDao cartItemDao;
	
	@Transactional
	public YarnStoreData saveYarnStore(YarnStoreData yarnStoreData) {
		YarnStore yarnStore = findOrCreateYarnStore(yarnStoreData.getYarnStoreId());
		copyYarnStoreFields(yarnStore, yarnStoreData);
		return new YarnStoreData(yarnStoreDao.save(yarnStore));
	}

	private YarnStore findOrCreateYarnStore(Long yarnStoreId) {
		if(Objects.isNull(yarnStoreId)) {
			return new YarnStore();
		} else {
			return findYarnStoreById(yarnStoreId);
		}
	}
	
	private YarnStore findYarnStoreById(Long yarnStoreId) {
		return yarnStoreDao.findById(yarnStoreId).orElseThrow(() -> new NoSuchElementException("Yarn Store with ID=" + yarnStoreId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<YarnStoreData> retrieveAllYarnStores() {
		List<YarnStore> yarnStores = yarnStoreDao.findAll();

		List<YarnStoreData> result = new LinkedList<>();

		for (YarnStore yarnStore : yarnStores) {
			YarnStoreData ysd = new YarnStoreData(yarnStore);

			ysd.getProducts().clear();
			ysd.getCustomers().clear();
			ysd.getCarts().clear();
			ysd.getCartItems().clear();
						
			result.add(ysd);
		}

		return result;
	}
	@Transactional(readOnly = true)
	public YarnStoreData retrieveYarnStoreById(Long yarnStoreId) {
		return new YarnStoreData(findYarnStoreById(yarnStoreId));
	}

	@Transactional(readOnly = false)
	public void deleteYarnStoreById(Long yarnStoreId) {
		YarnStore yarnStore = findYarnStoreById(yarnStoreId);
		yarnStoreDao.delete(yarnStore);
	}
	
	public void delete(Long yarnStoreId) {
		YarnStore yarnStore = findYarnStoreById(yarnStoreId);
		yarnStoreDao.delete(yarnStore);
	}
	
	@Transactional
	public ProductData saveProduct(Long yarnStoreId, ProductData product) {
	    Product existingProduct = findOrCreateProduct(product.getProductId());  
	    copyProductFields(existingProduct, product);
		return new ProductData(productDao.save(existingProduct));  
	   
	}
	
	private Product findOrCreateProduct(Long productId) {
		if (Objects.isNull(productId)) {
	        return new Product();  
	    } else {
	        return findProductById(productId); 
	    }
	}

	private Product findProductById(Long productId) {
		return productDao.findById(productId).orElseThrow(() -> new NoSuchElementException("Product with ID=" + productId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<Product> retrieveAllProducts() {
		List<Product> result = new LinkedList<>();

		return result;
	}
	
	@Transactional(readOnly = true)
	public ProductData retrieveProductById(Long productId) {
		return new ProductData(findProductById(productId));
	}

	@Transactional(readOnly = false)
	public void deleteProductById(Long productId) {
		Product product = findProductById(productId);
		productDao.delete(product);
	}
	
	@Transactional
	public CustomerData saveCustomer(Long customerId, CustomerData customer) {
	    Customer existingCustomer = findOrCreateCustomer(customerId);  
	    copyCustomerFields(existingCustomer, customer);  
	    return new CustomerData(customerDao.save(existingCustomer)); 
	}
	
	private Customer findOrCreateCustomer(Long customerId) {
		if (Objects.isNull(customerId)) {
	        return new Customer();  
	    } else {
	        return findCustomerById(customerId); 
	    }
	}

	private Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<CustomerData> retrieveAllCustomers() {
		List<CustomerData> result = new LinkedList<>();

		return result;
	}
	
	@Transactional(readOnly = true)
	public CustomerData retrieveCustomerById(Long customerId) {
		return new CustomerData(findCustomerById(customerId));
	}
	
	@Transactional
	public CartData saveCart(Long cartId, CartData cart) {
	    Cart existingCart = findOrCreateCart(cart.getCartId());  
	    copyCartFields(existingCart, cart);  
	    return new CartData(cartDao.save(existingCart)); 
	}
	
	private Cart findOrCreateCart(Long cartId) {
		if (Objects.isNull(cartId)) {
	        return new Cart();  
	    } else {
	        return findCartById(cartId); 
	    }
	}

	private Cart findCartById(Long cartId) {
		return cartDao.findById(cartId).orElseThrow(() -> new NoSuchElementException("Cart with ID=" + cartId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<CartData> retrieveAllCarts() {
		List<CartData> result = new LinkedList<>();

		return result;
	}
	
	@Transactional(readOnly = true)
	public CartData retrieveCartById(Long cartId) {
		return new CartData(findCartById(cartId));
	}

	@Transactional
	public CartItemData saveCartItem(Long cartItemId, CartItemData cart) {
	    CartItem existingCartItem = findOrCreateCartItem(cart.getCartItemId());  
	    copyCartItemFields(existingCartItem, cart);  
	    return new CartItemData(cartItemDao.save(existingCartItem)); 
	}
	
	private CartItem findOrCreateCartItem(Long cartItemId) {
		if (Objects.isNull(cartItemId)) {
	        return new CartItem();  
	    } else {
	        return findCartItemById(cartItemId); 
	    }
	}

	private CartItem findCartItemById(Long cartItemId) {
		return cartItemDao.findById(cartItemId).orElseThrow(() -> new NoSuchElementException("CartItem with ID=" + cartItemId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<CartItemData> retrieveAllCartItems() {
		List<CartItemData> result = new LinkedList<>();

		return result;
	}
	
	@Transactional(readOnly = true)
	public CartItemData retrieveCartItemById(Long cartItemId) {
		return new CartItemData(findCartItemById(cartItemId));
	}
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	public CartItemDao getCartItemDao() {
		return cartItemDao;
	}

	public void setCartItemDao(CartItemDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

		
	private void copyYarnStoreFields(YarnStore yarnStore, YarnStoreData yarnStoreData) {
		yarnStore.setYarnStoreName(yarnStoreData.getYarnStoreName());
		yarnStore.setYarnStoreAddress(yarnStoreData.getYarnStoreAddress());
		yarnStore.setYarnStoreCity(yarnStoreData.getYarnStoreCity());
		yarnStore.setYarnStoreState(yarnStoreData.getYarnStoreState());
		yarnStore.setYarnStoreZip(yarnStoreData.getYarnStoreZip());
		yarnStore.setYarnStorePhone(yarnStoreData.getYarnStorePhone());
	}
	
	private void copyProductFields(Product product, ProductData productData) {
		product.setProductId(productData.getProductId());
		product.setProductName(productData.getProductName());
		product.setProductDescription(productData.getProductDescription());
		product.setProductPrice(productData.getProductPrice());
		product.setProductStock(productData.getProductStock());
		
	}
	
	private void copyCustomerFields(Customer customer, CustomerData customerData) {
		customer.setCustomerId(customerData.getCustomerId());
		customer.setCustomerName(customerData.getCustomerName());
		customer.setCustomerPassword(customerData.getCustomerPassword());
		customer.setCustomerEmail(customerData.getCustomerEmail());
		customer.setCustomerRole(customerData.getCustomerRole());
	}
	
	private void copyCartFields(Cart cart, CartData cartData) {
		cart.setCartId(cart.getCartId());
		cart.setCartCreatedAt(cart.getCartCreatedAt());
		
	}
	
	private void copyCartItemFields(CartItem cartItem, CartItemData cartItemData) {
		cartItem.setCartItemId(cartItem.getCartItemId());
		cartItem.setCartItemQuantity(cartItem.getCartItemQuantity());
	}

	

	
	

	
	
	
}

