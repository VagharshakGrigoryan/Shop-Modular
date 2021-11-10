package am.onlinesite.onlinesitecommon.service;

import am.onlinesite.onlinesitecommon.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getCategories();

    void saveCategory(Category category);

    Optional<Category> categoryById(Long id);

    void deleteCategory(Long id);
}
