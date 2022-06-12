package io.order.manager.food.order.manager.services.impl;


import io.order.manager.food.order.manager.dto.CategoryDTO;
import io.order.manager.food.order.manager.entities.Category;
import io.order.manager.food.order.manager.mappers.CategoryMapper;
import io.order.manager.food.order.manager.repositories.CategoryRepository;
import io.order.manager.food.order.manager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import java.util.Optional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServicesImp implements CategoryService {
    private static final Logger log = Logger.getLogger(CategoryServicesImp.class);

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> listAll() {
        List<Category> allOrders = categoryRepository.findAll();
        return allOrders.stream().map(x -> categoryMapper.convertEntityToDto(x)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO save(CategoryDTO cat) {
        Category category = categoryRepository.save(categoryMapper.convertDtoToEntity(cat));
        return categoryMapper.convertEntityToDto(category);
    }

    @Override
    public void update(Long id, CategoryDTO categoryDTO) {
        if(!Objects.equals(id, categoryDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        Category category = categoryMapper.convertDtoToEntity(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public String delete(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return "order supprimé";
        } else {
            return "order non supprimé";
        }
    }


        /* -------------------------------------------------*/
    @Override
    public List<String> NomsCategories() {
        log.info("** commencer ListCategories() : ");
        List<String> noms = null;
        try {
            noms = categoryRepository.CatNames();
            log.debug(" Les noms des catégories: "+noms);
            log.info("** terminer NomsCategories() sans erreurs ! ");
        }catch(Exception e ){
            log.info("** terminer NomsCategories() avec erreurs : "+e);
        }
        return noms;
    }
    @Override
    public List<Category> ListCategories() {
        log.info("** Commencer  ListCategories() : ");
        List<Category> categoryList = null;
        try {
            categoryList = (List<Category>) categoryRepository.findAll();
            log.debug(" --  La liste des catégories :" +categoryList.toString() );
            log.debug(" -- Le nombre des catégories :" +categoryList.size() );
            log.info("** Terminer ListCategories() sans erreurs! ");
        }catch(Exception e ){
            log.info("** Terminer ListCategories() avec erreurs!  : "+e);
        }
        return categoryList ;
    }
    @Override
    public Long AjouterCategorie(Category category) {
        log.info("Commencer AjouterCategorie() : ");
        log.debug("Ajout de la catégorie " + category);
        try {
            categoryRepository.save(category);
            log.debug("Ajout Categorie fait !!!");
            log.info("Terminer AjouterCategorie() sans erreurs!");

        } catch (Exception e) {
            log.error("Terminer AjouterCategorie() avec erreurs : " + e);
        }

        return category.getId();
    }
    @Override
    public void ModifierNomCategorie(String nom, Long categoryID) {
        log.info("** Commencer MettreAjourNomCategorie(): ");
        try {
            log.debug(" La catégorie" + categoryID +" aura un nouveau nom :" + nom );
            categoryRepository.ModifierNomCategorieParID(nom, categoryID);
            log.debug(" succès de la mise à jour de nom pour la catégorie "+categoryID);
            log.info("** Terminer MettreAjourNomCategorie() sans erreurs !  ");
        }catch (Exception e ){
            log.info("** Terminer MettreAjourNomCategorie() avec erreurs: "+e);
        }

    }

    @Override
    public void SupprimerCategorie(Long categorieID) {
        log.info("Commencer SupprimerCategorie() : ");
        log.debug("Supperssion de la catégorie " + categorieID);
        Optional<Category> categorie = categoryRepository.findById(categorieID);
        try {
            categorie.ifPresent(cat -> categoryRepository.delete(cat));
            log.debug("Suppression faite !!!");
            log.info("Terminer SupprimerCategorie() sans erreurs!");
        } catch (Exception e) {
            log.error("Terminer SupprimerCategorie() avec erreurs : " + e);
        }
    }
}
