package am.onlinesite.onlinesitecommon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(min = 3, message = "User name should be 3 characters long")
    private String username;
    @Column(nullable = false, name = "first_name")
    @NotBlank
    private String firstName;
    @Column(nullable = false, name = "last_name")
    @NotBlank
    private String lastName;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 4, message = "Password should be 3 characters long")
    private String password;
    @Column(nullable = false)
    @NotBlank
    private String email;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN

    }

    private boolean isEmailVerified;
    private UUID token;


    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setUser(this);
    }
}
