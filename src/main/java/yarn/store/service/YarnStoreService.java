package yarn.store.service;


import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yarn.store.controller.model.YarnStoreData;
import yarn.store.controller.model.YarnStoreData.YarnStorePrice;
import yarn.store.controller.model.YarnStoreData.YarnStoreProduct;
import yarn.store.dao.PriceDao;
import yarn.store.dao.ProductDao;
import yarn.store.dao.YarnStoreDao;
import yarn.store.entity.Price;
import yarn.store.entity.Product;
import yarn.store.entity.YarnStore;



@Service
public class YarnStoreService {
	@Autowired
	private YarnStoreDao yarnStoreDao;
	@Autowired
	private PriceDao priceDao;
	@Autowired
	private ProductDao productDao;
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
			YarnStoreData psd = new YarnStoreData(yarnStore);

			psd.getProducts().clear();
			psd.getPrices().clear();

			result.add(psd);
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

	private void copyYarnStoreFields(YarnStore yarnStore, YarnStoreData yarnStoreData) {
		yarnStore.setYarnStoreName(yarnStoreData.getYarnStoreName());
		yarnStore.setYarnStoreAddress(yarnStoreData.getYarnStoreAddress());
		yarnStore.setYarnStoreCity(yarnStoreData.getYarnStoreCity());
		yarnStore.setYarnStoreState(yarnStoreData.getYarnStoreState());
		yarnStore.setYarnStoreZip(yarnStoreData.getYarnStoreZip());
		yarnStore.setYarnStorePhone(yarnStoreData.getYarnStorePhone());
	}

	private void copyPriceFields(Price price, YarnStorePrice yarnStorePrice) {
		price.setPriceId(yarnStorePrice.getPriceId());
	    price.setPriceAmount(yarnStorePrice.getPriceAmount());	
	}

	private void copyProductFields(Product product, YarnStoreProduct yarnStoreProduct) {
		product.setProductId(yarnStoreProduct.getProductId());
		product.setProductCategory(yarnStoreProduct.getProductCategory());
		product.setProductColor(yarnStoreProduct.getProductColor());
		product.setProductPrice(yarnStoreProduct.getProductPrice());
		
	}

	public void deleteYarnStoreByID(Long yarnStoreId) {
		YarnStore yarnStore = findYarnStoreById(yarnStoreId);
		yarnStoreDao.delete(yarnStore);
	}
}
