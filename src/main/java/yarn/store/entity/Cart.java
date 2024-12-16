package yarn.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	private Long customerId;
	
	private String cartCreatedAt;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Customer> customers = new HashSet<Customer>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_item_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Cart> carts = new HashSet<Cart>();

	@OneToOne(mappedBy = "cart", cascade = CascadeType.PERSIST)
	@JoinTable(name = "order", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Order order;
}
