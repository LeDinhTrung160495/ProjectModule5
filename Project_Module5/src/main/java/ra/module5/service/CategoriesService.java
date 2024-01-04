package ra.module5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.module5.model.Categories;

public interface CategoriesService {
    Page<Categories> findAll(Pageable pageable);
}
