package io.order.manager.food.order.manager.services;

import io.order.manager.food.order.manager.dto.CategoryDTO;
import io.order.manager.food.order.manager.entities.Category;

import java.util.List;

public interface CategoryService {
     List<CategoryDTO> listAll();
     CategoryDTO save(CategoryDTO cat);
     String delete(Long id);
     void update(Long id, CategoryDTO categoryDTO);

     /* -------------------------------------------------*/
     List<String> NomsCategories();
     List<Category> ListCategories();
     Long AjouterCategorie(Category category);
     void ModifierNomCategorie(String nom, Long CategoryID);

     void SupprimerCategorie(Long categorieID);
}
