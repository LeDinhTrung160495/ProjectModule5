package ra.module5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.module5.model.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

}
