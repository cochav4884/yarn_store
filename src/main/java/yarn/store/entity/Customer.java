package yarn.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	private String customerName;
	private String customerPassword;
	private String customerEmail;
	private String customerRole;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "yarn_store_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private YarnStore yarnStore;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Order order;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Cart cart;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "review_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Review review;
}
