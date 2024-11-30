package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

}
