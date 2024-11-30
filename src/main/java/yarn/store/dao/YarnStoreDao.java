package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.YarnStore;

public interface YarnStoreDao extends JpaRepository<YarnStore, Long> {

}
