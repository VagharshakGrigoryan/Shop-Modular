package am.onlinesite.onlinesitecommon.model;

import am.onlinesite.onlinesitecommon.model.items.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="total_price")
	private double totalPrice;
	
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();
	

	public void addItem(Item item) {
		  items.add(item);
	      item.setCart(this);
	}
    
	public void calculateTotalPrice() {
		double total = 0;
		
		for(Item item : items) {
			total += item.getProduct().getPrice() * item.getQuantity();
		}
		
		this.setTotalPrice(total);
	}
	
}