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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class YarnStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long yarnStoreId;
	
	private String yarnStoreName;
	private String yarnStoreAddress;
	private String yarnStoreCity;
	private String yarnStoreState;
	private String yarnStoreZip;
	private String yarnStorePhone;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "yarn_store_product", joinColumns = @JoinColumn(name = "yarn_store_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Product> products = new HashSet<>();

	@OneToMany(mappedBy = "yarnStore", cascade = CascadeType.PERSIST)
	@JoinTable(name = "yarn_store_customer", joinColumns = @JoinColumn(name = "yarn_store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Customer> customers = new HashSet<>();

	@OneToMany(mappedBy = "yarnStore", cascade = CascadeType.PERSIST)
	@JoinTable(name = "yarn_store_order", joinColumns = @JoinColumn(name = "yarn_store_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Order> orders = new HashSet<>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "cart", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "cart_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Cart> carts = new HashSet<>();
}
