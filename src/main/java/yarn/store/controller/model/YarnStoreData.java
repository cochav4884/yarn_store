package yarn.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import yarn.store.entity.Cart;
import yarn.store.entity.CartItem;
import yarn.store.entity.Product;
import yarn.store.entity.Customer;
import yarn.store.entity.YarnStore;

@Data
@NoArgsConstructor
public class YarnStoreData {
	private Long yarnStoreId;
	private String yarnStoreName;
	private String yarnStoreAddress;
	private String yarnStoreCity;
	private String yarnStoreState;
	private String yarnStoreZip;
	private String yarnStorePhone;
	private Set<ProductData> products = new HashSet<>();
	private Set<CustomerData> customers = new HashSet<>();
	private Set<CartData> carts = new HashSet<>();
	private Set<CartItemData> cartItems = new HashSet<>();
	
	
	public YarnStoreData(YarnStore yarnStore) {
		yarnStoreId = yarnStore.getYarnStoreId();
		yarnStoreName = yarnStore.getYarnStoreName();
		yarnStoreAddress = yarnStore.getYarnStoreAddress();
		yarnStoreCity = yarnStore.getYarnStoreCity();
		yarnStoreState = yarnStore.getYarnStoreState();
		yarnStoreZip = yarnStore.getYarnStoreZip();
		yarnStorePhone = yarnStore.getYarnStorePhone();
		
		for(Product product : yarnStore.getProducts()) {
			products.add(new ProductData(product));
		}
		
		for(Customer customer : yarnStore.getCustomers()) {
			customers.add(new CustomerData(customer));
		}
		
		for(Cart cart : yarnStore.getCarts()) {
			carts.add(new CartData(cart));
		}
		
		for(CartItem cartItem : yarnStore.getCartItems()) {
			cartItems.add(new CartItemData(cartItem));
		}
		
	}

	@Data
	@NoArgsConstructor
	public static class ProductData {
		private Long productId;
		
		private String productName;
		private String productDescription;
		private String productPrice;
		private String productStock;
		
		public ProductData(Product product) {
			productId = product.getProductId();
			
			productName = product.getProductName();
			productDescription = product.getProductDescription();
			productPrice = product.getProductPrice();
			productStock = product.getProductStock();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class CustomerData {
		private Long customerId;
		
		private String customerName;
		private String customerPassword;
		private String customerEmail;
		private String customerRole;
		
		public CustomerData(Customer customer) {
			customerId = customer.getCustomerId();
			
			customerName = customer.getCustomerName();
			customerPassword = customer.getCustomerPassword();
			customerEmail = customer.getCustomerEmail();
			customerRole = customer.getCustomerRole();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class CartData {
		private Long cartId;
				
		private String cartCreatedAt;
		
		public CartData(Cart cart) {
			cartId = cart.getCartId();
			cartCreatedAt = cart.getCartCreatedAt();
		}
	}

	@Data
	@NoArgsConstructor
	public static class CartItemData {
		private Long cartItemId;
				
		private String cartItemQuantity;
		
		public CartItemData(CartItem cartItem) {
			cartItemId = cartItem.getCartItemId();
			cartItemQuantity = cartItem.getCartItemQuantity();
		}
	}
}
