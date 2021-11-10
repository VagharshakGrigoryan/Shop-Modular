package am.onlinesite.onlinesiteweb.controller;

import am.onlinesite.onlinesitecommon.model.Cart;
import am.onlinesite.onlinesitecommon.model.Order;
import am.onlinesite.onlinesitecommon.model.User;
import am.onlinesite.onlinesitecommon.model.items.OrderItem;
import am.onlinesite.onlinesitecommon.repasitory.CartRepository;
import am.onlinesite.onlinesitecommon.repasitory.UserRepository;
import am.onlinesite.onlinesitecommon.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order/current")
@SessionAttributes("cart")
public class OrderController {

    private final CartRepository cartRepo;
    private final UserRepository userRepo;

    @Autowired
    public OrderController(CartRepository cartRepo, UserRepository userRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute("order")
    public Order order() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser currentUser = (CurrentUser) auth.getPrincipal();

        Order order = new Order();
        order.setUser(currentUser.getUser());

        return order;
    }

    @GetMapping
    public String showOrderForm() {
        return "order";
    }

    @PostMapping
    public String processOrderForm(@Valid Order order, Errors errors, @ModelAttribute("cart")
            Cart cart, SessionStatus sessionStatus) {

        if (errors.hasErrors())
            return "orderForm";

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(item -> new OrderItem(item.getQuantity(), item.getProduct()))
                .collect(Collectors.toList());

        orderItems.forEach(order::addOrderItem);
        order.setTotal_price(cart.getTotalPrice());

        Cart savedCart = cartRepo.save(cart);

        User user = order.getUser();
        user.addOrder(order);
        user.setCart(savedCart);

        userRepo.save(user);

        sessionStatus.isComplete();

        return "redirect:/";

    }
}