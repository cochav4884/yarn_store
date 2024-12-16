package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {

}
