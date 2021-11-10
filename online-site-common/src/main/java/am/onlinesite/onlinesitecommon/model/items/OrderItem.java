package am.onlinesite.onlinesitecommon.model.items;

import am.onlinesite.onlinesitecommon.model.Order;
import am.onlinesite.onlinesitecommon.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderItem {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;

	public OrderItem(int quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

}
