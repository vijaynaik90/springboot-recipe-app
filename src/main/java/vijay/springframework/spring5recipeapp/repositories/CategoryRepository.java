package vijay.springframework.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import vijay.springframework.spring5recipeapp.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByDescription(String description);
}
