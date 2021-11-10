package am.onlinesite.onlinesitecommon.service.impl;

import am.onlinesite.onlinesitecommon.model.Product;
import am.onlinesite.onlinesitecommon.repasitory.ProductRepository;
import am.onlinesite.onlinesitecommon.service.ProductService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@CacheConfig(cacheNames={"products"})
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private List<Product> products = new ArrayList<>();


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {

        return new ArrayList<>(productRepository.findAll());
    }

    @Override
    public Page<Product> productpages(PageRequest pageRequest) {

        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }


    @Override
    public List<Product> getProductsByPage(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    public Optional<Product> findId(Long id) {
        return
                products.stream()
                        .filter(product -> Objects.equals(product.getId(), id))
                        .findFirst();
    }

    public Product get(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }

    public Optional<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();

    }

    @Cacheable
    public List<Product> findAllProduct() {
        this.products = productRepository.findAll();

        return this.products;
    }

}