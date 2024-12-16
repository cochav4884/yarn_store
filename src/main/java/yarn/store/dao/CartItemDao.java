package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Long> {

}
