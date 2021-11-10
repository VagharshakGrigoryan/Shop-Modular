package am.onlinesite.onlinesitecommon.model.items;

import am.onlinesite.onlinesitecommon.model.Cart;
import am.onlinesite.onlinesitecommon.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


    public Item(Product product) {
        this.product = product;
    }

    public void updateQuantity() {
        this.quantity++;
    }

    public void setQuantityToOne() {
        this.quantity = 1;
    }


}
