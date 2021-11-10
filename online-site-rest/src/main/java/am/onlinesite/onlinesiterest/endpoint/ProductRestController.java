package am.onlinesite.onlinesiterest.endpoint;

import am.onlinesite.onlinesitecommon.model.Category;
import am.onlinesite.onlinesitecommon.model.Product;
import am.onlinesite.onlinesitecommon.model.Transaction;
import am.onlinesite.onlinesitecommon.service.CategoryService;
import am.onlinesite.onlinesitecommon.service.ProductService;
import am.onlinesite.onlinesitecommon.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class ProductRestController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final TransactionService transactionService;

    @RequestMapping("/products")
    private List<Product> getProducts() {

        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    private Product getProduct(@PathVariable Long id) {

        return productService.getProduct(id);
    }
    @GetMapping("/product/category/{categoryId}")
    private List<Product> getProductsByCategory(@PathVariable Long categoryId) {

        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping("/products")
    private void saveProduct(@RequestBody Product product) {

        productService.saveProduct(product);
    }

    @GetMapping("/categories")
    private List<Category> getCategories() {

        return categoryService.getCategories();
    }

    @PostMapping("/categories")
    private void saveCategory(@RequestBody Category category) {

        categoryService.saveCategory(category);
    }
    
    @GetMapping("/transactions")
    private List<Transaction> getTransactions() {

        return transactionService.getTransactions();
    }
}
