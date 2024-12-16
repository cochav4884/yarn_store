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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Long customerId;
	
	private String orderDate;
	private String orderStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Order order;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	@JoinTable(name = "orderItem", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "order_item_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<OrderItem> orderItems = new HashSet<>();

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	@JoinTable(name = "customer", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Customer> customers = new HashSet<>();
}
