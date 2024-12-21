package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.Product;
import yarn.store.entity.YarnStore;

public interface ProductDao extends JpaRepository<Product, Long> {

	YarnStore save(yarn.store.controller.model.YarnStoreData.ProductData existingProduct);

}
