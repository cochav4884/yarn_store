package yarn.store.service;


import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yarn.store.controller.model.YarnStoreData;
import yarn.store.controller.model.YarnStoreData.YarnStoreCart;
import yarn.store.controller.model.YarnStoreData.YarnStoreCartItem;
import yarn.store.controller.model.YarnStoreData.YarnStoreCustomer;
import yarn.store.controller.model.YarnStoreData.YarnStoreOrder;
import yarn.store.controller.model.YarnStoreData.YarnStoreOrderItem;
import yarn.store.controller.model.YarnStoreData.YarnStorePrice;
import yarn.store.controller.model.YarnStoreData.YarnStoreProduct;
import yarn.store.controller.model.YarnStoreData.YarnStoreReview;
import yarn.store.dao.CartDao;
import yarn.store.dao.CustomerDao;
import yarn.store.dao.OrderDao;
import yarn.store.dao.OrderItemDao;
import yarn.store.dao.PriceDao;
import yarn.store.dao.ProductDao;
import yarn.store.dao.YarnStoreDao;
import yarn.store.entity.Cart;
import yarn.store.entity.CartItem;
import yarn.store.entity.Customer;
import yarn.store.entity.Order;
import yarn.store.entity.OrderItem;
import yarn.store.entity.Price;
import yarn.store.entity.Product;
import yarn.store.entity.Review;
import yarn.store.entity.YarnStore;



@Service
public class YarnStoreService {
	@Autowired
	private YarnStoreDao yarnStoreDao;
	@Autowired
	private PriceDao priceDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CartDao cartDao;
	
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

	private Price findPriceById(Long yarnStoreId, Long priceId) {
		Price price = priceDao.findById(priceId).orElseThrow(() -> new NoSuchElementException("Price with ID=" + priceId + " was not found."));

		if (price.getYarnStore().getYarnStoreId() != yarnStoreId) {
			throw new IllegalArgumentException("The price with Id=" + priceId
					+ " is not employed by the yarn store with ID=" + yarnStoreId + ".");
		}

		return price;
	}

	private Product findProductById(Long yarnStoreId, Long productId) {
		Product product = productDao.findById(productId).orElseThrow(() -> new NoSuchElementException("Product with ID=" + productId + " was not found."));
		
		boolean found = false;
		
		for(YarnStore yarnStore : product.getYarnStores()) {
			if(yarnStore.getYarnStoreId() == yarnStoreId) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("The product with ID=" + productId + " is not a member of the yarn store with ID=" + yarnStoreId);
		}
		
		return product;
	}

	@Transactional(readOnly = false)
	public YarnStorePrice savePrice(Long yarnStoreId, YarnStorePrice yarnStorePrice) {
		YarnStore yarnStore = findYarnStoreById(yarnStoreId);
		Long priceId = yarnStorePrice.getPriceId();
		Price price = findOrCreatePrice(yarnStoreId, priceId);

		copyPriceFields(price, yarnStorePrice);

		price.setYarnStore(yarnStore);
		yarnStore.getPrices().add(price);

		Price dbPrice = priceDao.save(price);

		return new YarnStorePrice(dbPrice);
	}

	private Price findOrCreatePrice(Long yarnStoreId, Long priceId) {
		if(Objects.isNull(priceId)) {
			return new Price();
		} else {
			return findPriceById(yarnStoreId, priceId);
		}
	
	}

	@Transactional
	public YarnStoreProduct saveProduct(Long yarnStoreId, YarnStoreProduct yarnStoreProduct) {
		YarnStore yarnStore = findYarnStoreById(yarnStoreId);
		Long productId = yarnStoreProduct.getProductId();
		Product product = findOrCreateProduct(yarnStoreId, productId);

		copyProductFields(product, yarnStoreProduct);

		product.getYarnStores().add(yarnStore);
		yarnStore.getProducts().add(product);

		Product dbProduct = productDao.save(product);

		return new YarnStoreProduct(dbProduct);
	}

	private YarnStore findYarnStoreById(Long yarnStoreId) {
		return yarnStoreDao.findById(yarnStoreId).orElseThrow(() -> new NoSuchElementException("Yarn Store with ID=" + yarnStoreId + " was not found."));
	}

	private Product findOrCreateProduct(Long yarnStoreId, Long productId) {
		if(Objects.isNull(productId)) {
			return new Product();
		} else {
			return findProductById(yarnStoreId, productId);
		}
	}

	@Transactional(readOnly = true)
	public List<YarnStoreData> retrieveAllYarnStores() {
		List<YarnStore> yarnStores = yarnStoreDao.findAll();

		List<YarnStoreData> result = new LinkedList<>();

		for (YarnStore yarnStore : yarnStores) {
			YarnStoreData ysd = new YarnStoreData(yarnStore);

			ysd.getProducts().clear();
			ysd.getPrices().clear();
			ysd.getOrders().clear();
			
			result.add(ysd);
		}

		return result;
	}

	@Transactional(readOnly = true)
	public YarnStoreData retrieveYarnStoreById(Long yarnStoreId) {
		return new YarnStoreData(findYarnStoreById(yarnStoreId));
	}

	public List<YarnStoreOrder> retrieveAllYarnStoreOrders() {
		List<Order> orders = orderDao.findAll();

		List<YarnStoreOrder> result = new LinkedList<>();

		for (Order order : orders) {
			YarnStoreOrder yso = new YarnStoreOrder(order);
			
			yso.getOrderItems().clear();
			
			result.add(yso);
		}
		
		return result;
	}

	public YarnStoreOrder retrieveYarnStoreOrderById(Long yarnStoreOrderId) {
		return yarnStoreOrderId;
	}

	public List<YarnStoreProduct> retrieveAllYarnStoreProducts() {
		
		return null;
	}

	@Transactional(readOnly = false)
	public void deleteYarnStoreById(Long yarnStoreId) {
		YarnStore yarnStore = findYarnStoreById(yarnStoreId);
		yarnStoreDao.delete(yarnStore);
	}
	
	
	private void copyYarnStoreFields(YarnStore yarnStore, YarnStoreData yarnStoreData) {
		yarnStore.setYarnStoreName(yarnStoreData.getYarnStoreName());
		yarnStore.setYarnStoreAddress(yarnStoreData.getYarnStoreAddress());
		yarnStore.setYarnStoreCity(yarnStoreData.getYarnStoreCity());
		yarnStore.setYarnStoreState(yarnStoreData.getYarnStoreState());
		yarnStore.setYarnStoreZip(yarnStoreData.getYarnStoreZip());
		yarnStore.setYarnStorePhone(yarnStoreData.getYarnStorePhone());
	}
	
	private void copyProductFields(Product product, YarnStoreProduct yarnStoreProduct) {
		product.setProductId(yarnStoreProduct.getProductId());
		product.setYarnStoreId(yarnStoreProduct.getYarnStoreId());
		product.setProductName(yarnStoreProduct.getProductName());
		product.setProductDescription(yarnStoreProduct.getProductDescription());
		product.setProductPrice(yarnStoreProduct.getProductPrice());
		product.setProductStock(yarnStoreProduct.getProductStock());
		
	}

	private void copyPriceFields(Price price, YarnStorePrice yarnStorePrice) {
		price.setPriceId(yarnStorePrice.getPriceId());
		price.setProductId(yarnStorePrice.getProductId());
	    price.setPriceAmount(yarnStorePrice.getPriceAmount());	
	    price.setPriceOldPrice(yarnStorePrice.getPriceOldPrice());
	    price.setPriceNewPrice(yarnStorePrice.getPriceNewPrice());
	    price.setPriceChangeDate(yarnStorePrice.getPriceChangeDate());
	}

	
	
	private void copyOrderFields(Order order, YarnStoreOrder yarnStoreOrder) {
		order.setOrderId(yarnStoreOrder.getOrderId());
		order.setCustomerId(yarnStoreOrder.getCustomerId());
		order.setOrderDate(yarnStoreOrder.getOrderDate());
		order.setOrderStatus(yarnStoreOrder.getOrderStatus());
		
	}
	
	private void copyOrderItemFields(OrderItem orderItem, YarnStoreOrderItem yarnStoreOrderItem) {
		orderItem.setOrderItemId(yarnStoreOrderItem.getOrderItemId());
		orderItem.setOrderId(yarnStoreOrderItem.getOrderId());
		orderItem.setProductId(yarnStoreOrderItem.getProductId());
		orderItem.setOrderItemQuantity(yarnStoreOrderItem.getOrderItemQuantity());
		orderItem.setOrderPriceAtPurchase(yarnStoreOrderItem.getOrderPriceAtPurchase());
	}
	
	private void copyCustomerFields(Customer customer, YarnStoreCustomer yarnStoreCustomer) {
		customer.setCustomerId(yarnStoreCustomer.getCustomerId());
		customer.setCustomerName(yarnStoreCustomer.getCustomerName());
		customer.setCustomerPassword(yarnStoreCustomer.getCustomerPassword());
		customer.setCustomerEmail(yarnStoreCustomer.getCustomerEmail());
		customer.setCustomerRole(yarnStoreCustomer.getCustomerRole());
	}
	
	private void copyCartFields(Cart cart, YarnStoreCart yarnStoreCart) {
		cart.setCartId(yarnStoreCart.getCartId());
		cart.setCustomerId(yarnStoreCart.getCustomerId());
		cart.setCartCreatedAt(yarnStoreCart.getCartCreatedAt());
		
	}
	
	private void copyCartItemFields(CartItem cartItem, YarnStoreCartItem yarnStoreCartItem) {
		cartItem.setCartId(yarnStoreCartItem.getCartItemId());
		cartItem.setCartId(yarnStoreCartItem.getCartId());
		cartItem.setProductId(yarnStoreCartItem.getProductId());
		cartItem.setCartItemQuantity(yarnStoreCartItem.getCartItemQuantity());
	}
	
	private void copyReviewFields(Review review, YarnStoreReview yarnStoreReview) {
		review.setReviewId(yarnStoreReview.getReviewId());
		review.setCustomerId(yarnStoreReview.getCustomerId());
		review.setProductId(yarnStoreReview.getProductId());
		review.setReviewRating(yarnStoreReview.getReviewRating());
		review.setReviewText(yarnStoreReview.getReviewText());
	}

	public void d(Long yarnStoreId) {
		YarnStore yarnStore = findYarnStoreById(yarnStoreId);
		yarnStoreDao.delete(yarnStore);
	}
	
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}

	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
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

	
	

	

	
}

