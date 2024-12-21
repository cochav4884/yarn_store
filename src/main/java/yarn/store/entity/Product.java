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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Product {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String productName;
	private String productDescription;
	private String productPrice;
	private String productStock;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "yarn_store_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<YarnStore> yarnStores = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "cartItem", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "cart_item_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<CartItem> CartItems = new HashSet<>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Customer customer;

	public void deleteProductById(Product productId) {
		
	}

}
