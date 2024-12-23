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
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    private String cartItemQuantity;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "cart_item_product", 
        joinColumns = @JoinColumn(name = "cart_item_id"), 
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cart cart;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "cart_item_yarn_store",
        joinColumns = @JoinColumn(name = "cart_item_id"),
        inverseJoinColumns = @JoinColumn(name = "yarn_store_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private YarnStore yarnStore;


	public void deleteCartItemById(CartItem cartItemId) {
		
		
	}

	

}
