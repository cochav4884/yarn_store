package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {

}
