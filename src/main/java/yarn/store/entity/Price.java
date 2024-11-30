package yarn.store.entity;

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
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long priceId;

	private String priceAmount;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "yarn_store_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private YarnStore yarnStore;

}
