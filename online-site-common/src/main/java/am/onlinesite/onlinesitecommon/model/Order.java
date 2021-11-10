package am.onlinesite.onlinesitecommon.model;

import am.onlinesite.onlinesitecommon.model.items.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Zip is required")
	private String zip;
	
	@NotBlank(message="Street is required")
	private String street;
	
	@NotBlank(message="City is required")
	private String city;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
    
    private double total_price;
    
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public void addOrderItem(OrderItem item) {
    	 
    	 orderItems.add(item);
    	 item.setOrder(this);
    }
}
