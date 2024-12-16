package yarn.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import yarn.store.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {

}
