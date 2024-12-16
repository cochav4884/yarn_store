package yarn.store.controller;

import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.criteria.Order;
import lombok.extern.slf4j.Slf4j;
import yarn.store.controller.model.YarnStoreData;
import yarn.store.controller.model.YarnStoreData.YarnStoreCart;
import yarn.store.controller.model.YarnStoreData.YarnStoreCartItem;
import yarn.store.controller.model.YarnStoreData.YarnStoreOrder;
import yarn.store.controller.model.YarnStoreData.YarnStoreOrderItem;
import yarn.store.controller.model.YarnStoreData.YarnStorePrice;
import yarn.store.controller.model.YarnStoreData.YarnStoreProduct;
import yarn.store.controller.model.YarnStoreData.YarnStoreReview;
import yarn.store.controller.model.YarnStoreData.YarnStoreCustomer;
import yarn.store.service.YarnStoreService;

@RestController
@RequestMapping("/yarn_store")
@Slf4j
public class YarnStoreController {
	@Autowired
	private YarnStoreService yarnStoreService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreData insertYarnStore(@RequestBody YarnStoreData yarnStoreData) {
		log.info("Creating yart store {}", yarnStoreData);
		return yarnStoreService.saveYarnStore(yarnStoreData);
  }
	
	@PutMapping("/{yarnStoreId}")
	public YarnStoreData updateYarnStore(@PathVariable Long yarnStoreId, @RequestBody YarnStoreData yarnStoreData) {
		yarnStoreData.setYarnStoreId(yarnStoreId);
		log.info("Updating yarn store {}", yarnStoreData);
		return yarnStoreService.saveYarnStore(yarnStoreData);
	}
	
	@GetMapping
	public List<YarnStoreData> retrieveAllYarnStores() {
		log.info("Retrieving all yarn stores");
		return yarnStoreService.retrieveAllYarnStores();
	}
	
	@GetMapping("/{yarnStoreId}")
	public YarnStoreData retrieveYarnStoreById(@PathVariable Long yarnStoreId) {
		log.info("Retrieving yarn store with ID={}", yarnStoreId);
		return yarnStoreService.retrieveYarnStoreById(yarnStoreId);
	}
	
	@DeleteMapping("/{yarnStoreId}")
	public Map<String, String> deleteYarnStoreById(@PathVariable Long yarnStoreId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreId);
		
		yarnStoreService.deleteYarnStoreById(yarnStoreId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/product")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreProduct addProductToYarnStore(@PathVariable Long yarnStoreId,
			@RequestBody YarnStoreProduct yarnStoreProduct) {
		log.info("Adding product {} to yarn store with ID={}", yarnStoreProduct, yarnStoreId);

		return yarnStoreService.saveProduct(yarnStoreId, yarnStoreProduct);
	}
	
	@PutMapping("{yarnStoreId}/product/{productId}")
	public YarnStoreProduct modifyProduct(@PathVariable Long productId, @PathVariable Long yarnStoreId, @RequestBody YarnStoreProduct yarnStoreProduct) {
		yarnStoreProduct.setProductId(productId);
		log.info("Adding product {} to yarn store with ID={}", yarnStoreProduct, yarnStoreId);

		return yarnStoreService.saveProduct(yarnStoreId, yarnStoreProduct);
	}
	
	@GetMapping
	public List<YarnStoreProduct> retriveAllYarnStoreProduct() {
		log.info("Retrieving all yarnStoreProducts");
		return yarnStoreService.retrieveAllYarnStoreProducts();
	}
	
	@GetMapping("/{yarnStoreProductId}")
	public YarnStoreProduct retrieveYarnStoreProdcutById(@PathVariable Long yarnStoreProductId) {
		log.info("Retrieving yarnsStoreProduct with ID={}", yarnStoreProductId);
		return yarnStoreService.retrieveAllYarnStoreProducts();
	}
	
	@DeleteMapping("/products/{yarnStoreProductId}")
	public Map<String, String> deleteYarnStoreProductById(@PathVariable Long yarnStoreProductId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreProductId);
		
		yarnStoreProductId.deleteYarnStoreProductByID(yarnStoreProductId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreProductId + " deleted.");
	}
	
	
	@PostMapping("{yarnStoreId}/price")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStorePrice addPriceToYarnStore(@PathVariable Long yarnStoreId,
			@RequestBody YarnStorePrice yarnStorePrice) {
		log.info("Adding price {} to yarn store with ID={}", yarnStorePrice, yarnStoreId);

		return yarnStoreService.savePrice(yarnStoreId, yarnStorePrice);
	}
	
	@PutMapping("{yarnStoreId}/price/{priceId}")
	public YarnStorePrice modifyPrice(@PathVariable Long priceId, @PathVariable Long yarnStoreId, @RequestBody YarnStorePrice yarnStorePrice) {
		yarnStorePrice.setPriceId(priceId);
		log.info("Adding price {} to yarn store with ID={}", yarnStorePrice, yarnStoreId);

		return yarnStoreService.savePrice(yarnStoreId, yarnStorePrice);
	}
	
	@GetMapping
	public List<YarnStorePrice> retrieveAllYarnStorePrices() {
		log.info("Retrieving all yarnStorePrices");
		return yarnStoreService.retrieveAllYarnStorePrices();
	}
	
	@GetMapping("/{yarnStorePriceId}")
	public YarnStoreOrder retrieveYarnStorePriceById(@PathVariable Long yarnStorePriceId) {
		log.info("Retrieving yarnStorePrice with ID={}", yarnStorePriceId);
		return yarnStoreService.retrieveYarnStorePriceById(yarnStorePriceId);
	}
	
	@DeleteMapping("/prices/{yarnStorePriceId}")
	public Map<String, String> deleteYarnStorePriceById(@PathVariable Long yarnStorePriceId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStorePriceId);
		
		yarnStorePriceId.deleteYarnStorePriceByID(yarnStorePriceId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStorePriceId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/yarnStoreOrder")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreOrder addYarnStoreOrder(@PathVariable Long yarnStoreOrderId, @RequestBody YarnStoreOrder yarnStoreOrder) {
		log.info("Adding yarnStoreOrder {} to yarn store with ID={}", yarnStoreOrder, yarnStoreOrderId);
		
		return yarnStoreService.saveYarnStoreOrder(yarnStoreOrderId, yarnStoreOrder);
	}
	
	@PutMapping("{yarnStoreId}/yarnStoreOrder/{yarnStoreOrderId}")
	public YarnStoreOrder modifyYarnStoreOrder(@PathVariable Long yarnStoreOrderId, @PathVariable Long yarnStoreId, @RequestBody YarnStoreOrder yarnStoreOrder) {
		yarnStoreOrder.setOrderId(yarnStoreOrderId);
		log.info("Modifying yarnStoreOrder with ID={} for yarn store with ID={}", yarnStoreOrder, yarnStoreId);
		
		return yarnStoreService.saveYarnStoreOrder(yarnStoreId, yarnStoreOrder);
	}
	
	@GetMapping
	public List<YarnStoreOrder> retrieveAllYarnStoreOrders() {
		log.info("Retrieving all yarnStoreOrders");
		return yarnStoreService.retrieveAllYarnStoreOrders();
	}
	
	@GetMapping("/{yarnStoreOrderId}")
	public YarnStoreOrder retrieveYarnStoreOrderById(@PathVariable Long yarnStoreOrderId) {
		log.info("Retrieving yarnStoreOrder with ID={}", yarnStoreOrderId);
		return yarnStoreService.retrieveYarnStoreOrderById(yarnStoreOrderId);
	}
	
	@DeleteMapping("/orders/{yarnStoreOrderId}")
	public Map<String, String> deleteYarnStoreOrderById(@PathVariable Long yarnStoreOrderId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreOrderId);
		
		yarnStoreOrderId.deleteYarnStoreOrderByID(yarnStoreOrderId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreOrderId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/yarnStoreOrderItem")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreOrderItem addYarnStoreOrderItem(@PathVariable Long yarnStoreOrderItemId, @RequestBody YarnStoreOrderItem yarnStoreOrderItem) {
		log.info("Adding yarnStoreOrderItem {} to yarn store with ID={}", yarnStoreOrderItem, yarnStoreOrderItemId);
		
		return yarnStoreService.saveYarnStoreOrderItem(yarnStoreOrderItemId, yarnStoreOrderItem);
	}
	
	@PutMapping("{yarnStoreId}/yarnStoreOrderItem/{yarnStoreOrderItemId}")
	public YarnStoreOrderItem modifyYarnStoreOrderItem(@PathVariable Long yarnStoreOrderItemId, @PathVariable Long yarnStoreId, @RequestBody YarnStoreOrderItem yarnStoreOrderItem) {
		yarnStoreOrderItem.setOrderItemId(yarnStoreOrderItemId);
		log.info("Modifying yarnStoreOrderItem with ID={} for yarn store with ID={}", yarnStoreOrderItem, yarnStoreId);
		
		return yarnStoreService.saveYarnStoreOrderItem(yarnStoreId, yarnStoreOrderItem);
	}
	
	@GetMapping
	public List<YarnStoreOrderItem> retrieveAllYarnStoreOrderItems() {
		log.info("Retrieving all yarnStoreOrderItems");
		return yarnStoreService.retrieveAllYarnStoreOrderItems();
	}
	
	@GetMapping("/{yarnStoreOrderItemId}")
	public YarnStoreOrderItem retrieveYarnStoreYarnStoreOrderItemById(@PathVariable Long yarnStoreOrderItemId) {
		log.info("Retrieving yarnStoreOrderItem with ID={}", yarnStoreOrderItemId);
		return yarnStoreService.retrieveYarnStoreOrderItemById(yarnStoreOrderItemId);
	}
	
	@DeleteMapping("/orderItems/{yarnStoreOrderItemId}")
	public Map<String, String> deleteYarnStoreOrderItemById(@PathVariable Long yarnStoreOrderItemId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreOrderItemId);
		
		yarnStoreOrderItemId.deleteYarnStoreOrderItemByID(yarnStoreOrderItemId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreOrderItemId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/yarnStoreCustomer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreCustomer addYarnStoreCustomer(@PathVariable Long yarnStoreCustomerId, @RequestBody YarnStoreCustomer yarnStoreCustomer) {
		log.info("Adding yarnStoreCustomer {} to yarn store with ID={}", yarnStoreCustomer, yarnStoreCustomerId);
		
		return yarnStoreService.saveYarnStoreCustomer(yarnStoreCustomerId, yarnStoreCustomer);
	}
	
	@PutMapping("{yarnStoreId}/yarnStoreCustomer/{yarnStoreCustomerId}")
	public YarnStoreCustomer modifyYarnStoreCustomer(@PathVariable Long yarnStoreCustomerId, @PathVariable Long yarnStoreId, @RequestBody YarnStoreCustomer yarnStoreCustomer) {
		yarnStoreCustomer.setCustomerId(yarnStoreCustomerId);
		log.info("Modifying yarnStoreCustomer with ID={} for yarn store with ID={}", yarnStoreCustomer, yarnStoreId);
		
		return yarnStoreService.saveYarnStoreCustomer(yarnStoreId, yarnStoreCustomer);
	}
	
	@GetMapping
	public List<YarnStoreCustomer> retrieveAllYarnStoreCustomers() {
		log.info("Retrieving all yarnStoreCustomers");
		return yarnStoreService.retrieveAllYarnStoreCustomers();
	}
	
	@GetMapping("/{yarnStoreCustomerId}")
	public YarnStoreCustomer retrieveYarnStoreCustomerById(@PathVariable Long yarnStoreCustomerId) {
		log.info("Retrieving yarnStoreCustomer with ID={}", yarnStoreCustomerId);
		return yarnStoreService.retrieveYarnStoreCustomerById(yarnStoreCustomerId);
	}
	
	@DeleteMapping("/customers/{yarnStoreCustomerId}")
	public Map<String, String> deleteYarnStoreCustomerById(@PathVariable Long yarnStoreCustomerId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreCustomerId);
		
		yarnStoreCustomerId.deleteYarnStoreCustomerByID(yarnStoreCustomerId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreCustomerId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/yarnStoreCart")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreCart addYarnStoreCart(@PathVariable Long yarnStoreCartId, @RequestBody YarnStoreCart yarnStoreCart) {
		log.info("Adding yarnStoreCart {} to yarn store with ID={}", yarnStoreCart, yarnStoreCartId);
		
		return yarnStoreService.saveYarnStoreCart(yarnStoreCartId, yarnStoreCart);
	}
	
	@PutMapping("{yarnStoreId}/yarnStoreCart/{yarnStoreCartId}")
	public YarnStoreCart modifyYarnStoreCart(@PathVariable Long yarnStoreCartId, @PathVariable Long yarnStoreId, @RequestBody YarnStoreCart yarnStoreCart) {
		yarnStoreCart.setCartId(yarnStoreCartId);
		log.info("Modifying yarnStoreCart with ID={} for yarn store with ID={}", yarnStoreCart, yarnStoreId);
		
		return yarnStoreService.saveYarnStoreCart(yarnStoreId, yarnStoreCart);
	}
	
	@GetMapping
	public List<YarnStoreCart> retrieveAllYarnStoreCarts() {
		log.info("Retrieving all yarnStoreCarts");
		return yarnStoreService.retrieveAllYarnStoreCarts();
	}
	
	@GetMapping("/{yarnStoreCartId}")
	public YarnStoreCart retrieveYarnStoreCartById(@PathVariable Long yarnStoreCartId) {
		log.info("Retrieving yarnStoreCart with ID={}", yarnStoreCartId);
		return yarnStoreService.retrieveYarnStoreCartById(yarnStoreCartId);
	}
	
	@DeleteMapping("/carts/{yarnStoreCartId}")
	public Map<String, String> deleteYarnStoreCartById(@PathVariable Long yarnStoreCartId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreCartId);
		
		yarnStoreCartId.deleteYarnStoreCartByID(yarnStoreCartId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreCartId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/yarnStoreCartItem")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreCartItem addYarnStoreCartItem(@PathVariable Long yarnStoreCartItemId, @RequestBody YarnStoreCartItem yarnStoreCartItem) {
		log.info("Adding yarnStoreCartItem {} to yarn store with ID={}", yarnStoreCartItem, yarnStoreCartItemId);
		
		return yarnStoreService.saveYarnStoreCartItem(yarnStoreCartItemId, yarnStoreCartItem);
	}
	
	@PutMapping("{yarnStoreId}/yarnStoreCartItem/{yarnStoreCartItemId}")
	public YarnStoreCartItem modifyYarnStoreCartItem(@PathVariable Long yarnStoreCartItemId, @PathVariable Long yarnStoreId, @RequestBody YarnStoreCartItem yarnStoreCartItem) {
		yarnStoreCartItem.setCartItemId(yarnStoreCartItemId);
		log.info("Modifying yarnStoreCartItem with ID={} for yarn store with ID={}", yarnStoreCartItem, yarnStoreId);
		
		return yarnStoreService.saveYarnStoreCartItem(yarnStoreId, yarnStoreCartItem);
	}
	
	@GetMapping
	public List<YarnStoreCartItem> retrieveAllYarnStoreCartItems() {
		log.info("Retrieving all yarnStoreCartItems");
		return yarnStoreService.retrieveAllYarnStoreCartItems();
	}
	
	@GetMapping("/{yarnStoreCartItemId}")
	public YarnStoreCartItem retrieveYarnStoreCartItemById(@PathVariable Long yarnStoreCartItemId) {
		log.info("Retrieving yarnStoreCartItem with ID={}", yarnStoreCartItemId);
		return yarnStoreService.retrieveYarnStoreCartItemById(yarnStoreCartItemId);
	}
	
	@DeleteMapping("/cartItems/{yarnStoreCartItemId}")
	public Map<String, String> deleteYarnStoreCartItemById(@PathVariable Long yarnStoreCartItemId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreCartItemId);
		
		yarnStoreCartItemId.deleteYarnStoreCartItemByID(yarnStoreCartItemId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreCartItemId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/yarnStoreReview")
	@ResponseStatus(code = HttpStatus.CREATED)
	public YarnStoreReview addYarnStoreReview(@PathVariable Long yarnStoreReviewId, @RequestBody YarnStoreReview yarnStoreReview) {
		log.info("Adding yarnStoreReview {} to yarn store with ID={}", yarnStoreReview, yarnStoreReviewId);
		
		return yarnStoreService.saveYarnStoreReview(yarnStoreReviewId, yarnStoreReview);
	}
	
	@PutMapping("{yarnStoreId}/yarnStoreReview/{yarnStoreReviewId}")
	public YarnStoreReview modifyYarnStoreReview(@PathVariable Long yarnStoreReviewId, @PathVariable Long yarnStoreId, @RequestBody YarnStoreReview yarnStoreReview) {
		yarnStoreReview.setReviewId(yarnStoreReviewId);
		log.info("Modifying yarnStoreReview with ID={} for yarn store with ID={}", yarnStoreReview, yarnStoreId);
		
		return yarnStoreService.saveYarnStoreReview(yarnStoreId, yarnStoreReview);
	}
	
	@GetMapping
	public List<YarnStoreReview> retrieveAllYarnStoreReviews() {
		log.info("Retrieving all yarnStoreReviews");
		return yarnStoreService.retrieveAllYarnStoreReviews();
	}
	
	@GetMapping("/{yarnStoreReviewId}")
	public YarnStoreReview retrieveYarnStoreReviewById(@PathVariable Long yarnStoreReviewId) {
		log.info("Retrieving yarnStoreReview with ID={}", yarnStoreReviewId);
		return yarnStoreService.retrieveYarnStoreReviewById(yarnStoreReviewId);
	}
	
	@DeleteMapping("/reviews/{yarnStoreReviewId}")
	public Map<String, String> deleteYarnStoreReviewById(@PathVariable Long yarnStoreReviewId) throws AccountNotFoundException {
		log.info("Deleting yarn store with ID={}", yarnStoreReviewId);
		
		yarnStoreReviewId.deleteYarnStoreReviewByID(yarnStoreReviewId);
		
		return Map.of("message", "Yarn store with Id=" + yarnStoreReviewId + " deleted.");
	}
}