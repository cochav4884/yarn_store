package yarn.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Product {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String productCategory;
	private String productColor;
	private String productPrice;
	
	
	@ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
	  @EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  private Set<YarnStore> yarnStores = new HashSet<>();



}
