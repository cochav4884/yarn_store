package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.Price;

public interface PriceDao extends JpaRepository<Price, Long> {

}
