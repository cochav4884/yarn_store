package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
