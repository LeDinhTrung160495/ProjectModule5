package ra.module5.mapper;

import ra.module5.dto.request.CategoriesRequest;
import ra.module5.dto.response.CategoriesResponse;
import ra.module5.model.Categories;

public class MapperCategories implements MapperGeneric<Categories, CategoriesRequest, CategoriesResponse> {
    @Override
    public Categories mapperRequestToEntity(CategoriesRequest categoriesRequest) {
        return Categories.builder().catalogId(categoriesRequest.getCatalogId())
                .catalogName(categoriesRequest.getCatalogName())
                .description(categoriesRequest.getDescription())
                .catalogStatus(categoriesRequest.isCatalogStatus()).build();
    }

    @Override
    public CategoriesResponse mapperEntityToResponse(Categories categories) {
        return CategoriesResponse.builder().catalogId(categories.getCatalogId())
                .catalogName(categories.getCatalogName())
                .description(categories.getDescription())
                .catalogStatus(categories.isCatalogStatus()).build();
    }
}
