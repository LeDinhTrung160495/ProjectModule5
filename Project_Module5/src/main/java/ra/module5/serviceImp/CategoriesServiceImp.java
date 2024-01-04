package ra.module5.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.module5.model.Categories;
import ra.module5.repository.CategoriesRepository;
import ra.module5.service.CategoriesService;

@Service
public class CategoriesServiceImp implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public Page<Categories> findAll(Pageable pageable) {
        return categoriesRepository.findAll(pageable);
    }
}
