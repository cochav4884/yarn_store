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
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Product {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private Long yarnStoreId;
	private Long reviewId;
	
	private String productName;
	private String productDescription;
	private String productPrice;
	private String productStock;
	
	
	@ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  private Set<Product> products = new HashSet<>();

	@ManyToMany(mappedBy = "prices", cascade = CascadeType.PERSIST)
	@JoinTable(name = "price", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "price_id"))
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  private Set<Price> prices = new HashSet<>();

	
	@OneToMany(mappedBy = "OrderItem", cascade = CascadeType.PERSIST)
	@JoinTable(name = "orderItem", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_item_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<OrderItem> orderitems = new HashSet<>();

}
