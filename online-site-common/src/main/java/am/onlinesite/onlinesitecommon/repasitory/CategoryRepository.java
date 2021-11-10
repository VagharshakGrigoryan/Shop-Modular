package am.onlinesite.onlinesitecommon.repasitory;

import am.onlinesite.onlinesitecommon.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
