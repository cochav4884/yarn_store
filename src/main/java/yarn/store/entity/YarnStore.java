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
	@JoinTable(name = "yarn_store_customer", joinColumns = @JoinColumn(name = "yarn_store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Product> products = new HashSet<>();

	@OneToMany(mappedBy = "yarnStore", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Price> prices = new HashSet<>();

}