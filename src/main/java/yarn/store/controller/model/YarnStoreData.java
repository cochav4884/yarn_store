package yarn.store.controller.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import yarn.store.entity.Cart;
import yarn.store.entity.CartItem;
import yarn.store.entity.Order;
import yarn.store.entity.OrderItem;
import yarn.store.entity.Price;
import yarn.store.entity.Product;
import yarn.store.entity.Review;
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
	private Set<YarnStoreProduct> products = new HashSet<>();
	private Set<YarnStorePrice> prices = new HashSet<>(); 
	private Set<YarnStoreOrder> orders = new HashSet<>();
	
	
	public YarnStoreData(YarnStore yarnStore) {
		yarnStoreId = yarnStore.getYarnStoreId();
		yarnStoreName = yarnStore.getYarnStoreName();
		yarnStoreAddress = yarnStore.getYarnStoreAddress();
		yarnStoreCity = yarnStore.getYarnStoreCity();
		yarnStoreState = yarnStore.getYarnStoreState();
		yarnStoreZip = yarnStore.getYarnStoreZip();
		yarnStorePhone = yarnStore.getYarnStorePhone();
		
		for(Product product : yarnStore.getProducts()) {
			products.add(new YarnStoreProduct(product));
		}
		    
		for(Price price : yarnStore.getPrices()) {
			prices.add(new YarnStorePrice(price));
		}

		for(Order order : yarnStore.getOrders()) {
			orders.add(new YarnStoreOrder(order));
		}	
	}

	@Data
	@NoArgsConstructor
	public static class YarnStoreProduct {
		private Long productId;
		private Long yarnStoreId;
		private Long reviewId;
		private String productName;
		private String productDescription;
		private String productPrice;
		private String productStock;
		
		public YarnStoreProduct(Product product) {
			productId = product.getProductId();
			yarnStoreId = product.getYarnStoreId();
			reviewId = product.getReviewId();
			productName = product.getProductName();
			productDescription = product.getProductDescription();
			productPrice = product.getProductPrice();
			productStock = product.getProductStock();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStorePrice {
		private Long priceId;
		private Long productId;
				
		private String priceAmount;
		private String priceOldPrice;
		private String priceNewPrice;
		private String priceChangeDate;
		
		public YarnStorePrice(Price price) {
			priceId = price.getPriceId();
			productId = price.getProductId();
			priceAmount = price.getPriceAmount();
			priceOldPrice = price.getPriceOldPrice();
			priceNewPrice = price.getPriceNewPrice();
			priceChangeDate = price.getPriceChangeDate();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStoreOrder {
		private Long orderId;
		private Long customerId;
		
		private String orderDate;
		private String orderStatus;
		private Set<YarnStoreOrderItem> orderItems = new HashSet<>();
		
		public YarnStoreOrder(Order order) {
			orderId = order.getOrderId();
			customerId = order.getCustomerId();
			orderDate = order.getOrderDate();
			orderStatus = order.getOrderStatus();
			
			for(OrderItem orderItem : order.getOrderItems()) {
				orderItems.add(new YarnStoreOrderItem(orderItem));
			}
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStoreOrderItem {
		private Long orderItemId;
		private Long orderId;
		private Long productId;
		
		private String orderItemQuantity;
		private String orderPriceAtPurchase;
		
		public YarnStoreOrderItem(OrderItem orderItem) {
			orderItemId = orderItem.getOrderItemId();
			orderId = orderItem.getOrderId();
			productId = orderItem.getProductId();
			orderItemQuantity = orderItem.getOrderItemQuantity();
			orderPriceAtPurchase = orderItem.getOrderPriceAtPurchase();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStoreCustomer {
		private Long customerId;
		
		private String customerName;
		private String customerPassword;
		private String customerEmail;
		private String customerRole;
		
		public YarnStoreCustomer(Customer customer) {
			customerId = customer.getCustomerId();
			customerName = customer.getCustomerName();
			customerPassword = customer.getCustomerPassword();
			customerEmail = customer.getCustomerEmail();
			customerRole = customer.getCustomerRole();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStoreCart {
		private Long cartId;
		private Long customerId;
		
		private String cartCreatedAt;
		
		public YarnStoreCart(Cart cart) {
			cartId = cart.getCartId();
			customerId = cart.getCustomerId();
			cartCreatedAt = cart.getCartCreatedAt();
		}
	}

	@Data
	@NoArgsConstructor
	public static class YarnStoreCartItem {
		private Long cartItemId;
		private Long cartId;
		private Long productId;
		
		private String cartItemQuantity;
		
		public YarnStoreCartItem(CartItem cartItem) {
			cartItemId = cartItem.getCartItemId();
			cartId = cartItem.getCartId();
			productId = cartItem.getProductId();
			cartItemQuantity = cartItem.getCartItemQuantity();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStoreReview {
		private Long reviewId;
		private Long customerId;
		private Long productId;
		
		private String reviewRating;
		private String reviewText;
		
		public YarnStoreReview(Review review) {
			reviewId = review.getReviewId();
			customerId= review.getCustomerId();
			productId = review.getProductId();
			reviewRating = review.getReviewRating();
			reviewText = review.getReviewText();
			
		}
	}

}
