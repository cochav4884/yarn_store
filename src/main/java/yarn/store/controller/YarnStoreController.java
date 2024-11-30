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

import lombok.extern.slf4j.Slf4j;
import yarn.store.controller.model.YarnStoreData;
import yarn.store.controller.model.YarnStoreData.YarnStorePrice;
import yarn.store.controller.model.YarnStoreData.YarnStoreProduct;
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
		
		yarnStoreService.deleteYarnStoreByID(yarnStoreId);
		
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
}