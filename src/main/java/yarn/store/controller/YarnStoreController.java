package yarn.store.controller;

import java.util.List;
import java.util.Map;

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

import lombok.extern.slf4j.Slf4j;
import yarn.store.controller.model.YarnStoreData;
import yarn.store.controller.model.YarnStoreData.CartData;
import yarn.store.controller.model.YarnStoreData.CartItemData;
import yarn.store.controller.model.YarnStoreData.CustomerData;
import yarn.store.controller.model.YarnStoreData.ProductData;
import yarn.store.entity.Cart;
import yarn.store.entity.CartItem;
import yarn.store.entity.Customer;
import yarn.store.entity.Product;
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
	public Map<String, String> deleteYarnStoreById(@PathVariable Long yarnStoreId) {
		log.info("Deleting yarn store with ID={}", yarnStoreId);

		yarnStoreService.deleteYarnStoreById(yarnStoreId);

		return Map.of("message", "Yarn store with Id=" + yarnStoreId + " deleted.");
	}

	@PostMapping("{yarnStoreId}/product")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductData addProductToYarnStore(@PathVariable Long yarnStoreId, @RequestBody ProductData productData) {
		log.info("Adding product {} to yarn store with ID={}", yarnStoreService);

		return yarnStoreService.saveProduct(yarnStoreId, productData);
	}

	@PutMapping("{yarnStoreId}/product/{productId}")
	public ProductData modifyProduct(@PathVariable Long productId, @PathVariable Long yarnStoreId,
			@RequestBody ProductData productData) {
		productData.setProductId(productId);
		log.info("Modifying product with ID={}", productId);

		return yarnStoreService.saveProduct(yarnStoreId, productData);
	}

	@GetMapping("yarn_Store/products")
	public List<Product> retriveAllProduct() {
		log.info("Retrieving all products");
		return yarnStoreService.retrieveAllProducts();
	}

	@GetMapping("/{productId}")
	public ProductData retrieveProdcutById(@PathVariable Long productId) {
		log.info("Retrieving product with ID={}", productId);
		return yarnStoreService.retrieveProductById(productId);
	}

	@DeleteMapping("{productId}")
	public Map<String, String> deleteProductById(@PathVariable Product productId) {
		log.info("Deleting product with ID={}", productId);

		productId.deleteProductById(productId);

		return Map.of("message", "Product with Id=" + productId + " deleted.");
	}

	@PostMapping("{yarnStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData addCustomer(@PathVariable Long yarnStoreId, @RequestBody CustomerData customerData) {
		log.info("Adding Customer {} to yarn store with ID={}", customerData, yarnStoreId);

		return yarnStoreService.saveCustomer(yarnStoreId, customerData);
	}

	@PutMapping("{yarnStoreId}/customer/{customerId}")
	public CustomerData modifyCustomer(@PathVariable Long customerId, @PathVariable Long yarnStoreId,
			@RequestBody CustomerData customerData) {
		customerData.setCustomerId(customerId);
		log.info("Modifying customer with ID={} for yarn store with ID={}", customerData, yarnStoreId);

		return yarnStoreService.saveCustomer(yarnStoreId, customerData);
	}

	@GetMapping("yarn_Store/customers")
	public List<CustomerData> retrieveAllCustomers() {
		log.info("Retrieving all customers");
		return yarnStoreService.retrieveAllCustomers();
	}

	@GetMapping("/{customerId}")
	public CustomerData retrieveCustomerById(@PathVariable Long customerId) {
		log.info("Retrieving customer with ID={}", customerId);
		return yarnStoreService.retrieveCustomerById(customerId);
	}

	@DeleteMapping("{customerId}")
	public Map<String, String> deleteCustomerById(@PathVariable Customer customerId) {
		log.info("Deleting customer with ID={}", customerId);

		customerId.deleteCustomerById(customerId);

		return Map.of("message", "Customer with Id=" + customerId + " deleted.");
	}
	
	@PostMapping("{yarnStoreId}/cart")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CartData addCart(@PathVariable Long cartId, @RequestBody CartData cartData) {
		log.info("Adding cart {} to yarn store with ID={}", cartData, cartId);

		return yarnStoreService.saveCart(cartId, cartData);
	}

	@PutMapping("{yarnStoreId}/cart/{cartId}")
	public CartData modifyCart(@PathVariable Long cartId, @PathVariable Long yarnStoreId, @RequestBody CartData cartData) {
		cartData.setCartId(cartId);
		log.info("Modifying cart with ID={}", cartId);

		return yarnStoreService.saveCart(yarnStoreId, cartData);
	}

	@GetMapping("yarn_Store/carts")
	public List<CartData> retrieveAllCarts() {
		log.info("Retrieving all carts");
		return yarnStoreService.retrieveAllCarts();
	}

	@GetMapping("/{cartId}")
	public CartData retrieveCartById(@PathVariable Long cartId) {
		log.info("Retrieving cart with ID={}", cartId);
		return yarnStoreService.retrieveCartById(cartId);
	}

	@DeleteMapping("{cartId}")
	public Map<String, String> deleteCartById(@PathVariable Cart cartId) {
		log.info("Deleting cart with ID={}", cartId);

		cartId.deleteCartById(cartId);

		return Map.of("message", "Cart with Id=" + cartId + " deleted.");
	}

	@PostMapping("{yarnStoreId}/cartItem")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CartItemData addCartItem(@PathVariable Long yarnStoreId, @RequestBody CartItemData cartItemData) {
		log.info("Adding cartItem {} to yarn store with ID={}", cartItemData, yarnStoreId);

		return yarnStoreService.saveCartItem(yarnStoreId, cartItemData);
	}

	@PutMapping("{yarnStoreId}/cartItem/{cartItemId}")
	public CartItemData modifyCartItem(@PathVariable Long cartItemId, @PathVariable Long yarnStoreId,
			@RequestBody CartItemData cartItemData) {
		cartItemData.setCartItemId(cartItemId);
		log.info("Modifying cartItem with ID={}", cartItemId);

		return yarnStoreService.saveCartItem(yarnStoreId, cartItemData);
	}

	@GetMapping("yarn_Store/cartItems")
	public List<CartItemData> retrieveAllCartItems() {
		log.info("Retrieving all cartItems");
		return yarnStoreService.retrieveAllCartItems();
	}

	@GetMapping("/{cartItemId}")
	public CartItemData retrieveCartItemById(@PathVariable Long cartItemId) {
		log.info("Retrieving cartItem with ID={}", cartItemId);
		return yarnStoreService.retrieveCartItemById(cartItemId);
	}

	@DeleteMapping("/cartItems/{cartItemId}")
	public Map<String, String> deleteCartItemById(@PathVariable CartItem cartItemId) {
		log.info("Deleting cart item with ID={}", cartItemId);

		cartItemId.deleteCartItemById(cartItemId);

		return Map.of("message", "Cart item with Id=" + cartItemId + " deleted.");
	}

}