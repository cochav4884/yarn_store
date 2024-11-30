package yarn.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import yarn.store.entity.Price;
import yarn.store.entity.Product;
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
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStoreProduct {
		private Long productId;
		private String productCategory;
		private String productColor;
		private String productPrice;
		
		public YarnStoreProduct(Product product) {
			productId = product.getProductId();
			productCategory = product.getProductCategory();
			productColor = product.getProductColor();
			productPrice = product.getProductPrice();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class YarnStorePrice {
		private Long priceId;
		private String priceAmount;
		
		public YarnStorePrice(Price price) {
			priceId = price.getPriceId();
			priceAmount = price.getPriceAmount(); 
		}
	}

}
