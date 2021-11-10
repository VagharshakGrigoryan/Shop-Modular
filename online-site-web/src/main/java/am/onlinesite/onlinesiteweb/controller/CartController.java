package am.onlinesite.onlinesiteweb.controller;


import am.onlinesite.onlinesitecommon.model.Cart;
import am.onlinesite.onlinesitecommon.model.Product;
import am.onlinesite.onlinesitecommon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;


@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

	private final ProductService productService;
	
	@Autowired
	public CartController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public String showCart() {
		return "cart";
	}
	
	@PostMapping("/remove/{name}")
	public String removeCartItem(@PathVariable("name") String name, @ModelAttribute("cart") Cart cart) {
		  
		  Optional<Product> product = productService.findByName(name);
		
		  if(product.isPresent()) {
			  
			   cart.getItems().removeIf(item -> Objects.equals(item.getProduct().getId(), product.get().getId()));
			   
			   cart.calculateTotalPrice();
			  
		  }
		  
		  return "redirect:/cart";
	}
}
