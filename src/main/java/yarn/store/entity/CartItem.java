package yarn.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;
	private Long cartId;
	private Long productId;
	
	private String cartItemQuantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Cart> carts = new HashSet<>();

}
