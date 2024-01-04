package ra.module5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.module5.service.CategoriesService;

@RestController
@RequestMapping("api.myservice.com/v1/catalogs")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
//public
}
