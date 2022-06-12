package io.order.manager.food.order.manager.services.impl;

import io.order.manager.food.order.manager.dto.CategoryDTO;
import io.order.manager.food.order.manager.services.CategoryService;
import io.order.manager.food.order.manager.entities.Category;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServicesImpTest {
    @Autowired
    CategoryServicesImp categorySerImp;
    @Test
    public void TestNomsCategories() {
        List<String> nomsCategories = categorySerImp.NomsCategories();
        Assert.assertTrue(nomsCategories.size() > 0);
    }
    @Test
    public void TestListCategories() {
        List<Category> cat = categorySerImp.ListCategories();
        Assert.assertNotNull(cat);
    }
    @Test
    public void TestAjouterCategorie() throws ParseException {

        Category cat = new Category("skon", "skon");
        Long catId = categorySerImp.AjouterCategorie(cat);

        //assertEquals("check ajout employé ",employeId);
        Assert.assertEquals("vérifier l'ajout de la categorie", cat.getDescription(),
                cat.getNom());
    }
    @Test
    public void TestModifierNomCategorie() {
        String newnom = "supp";
        String newdes="";
        categorySerImp.ModifierNomCategorie(newnom, categorySerImp.ListCategories().get(0).getId());
        String nomupdated = categorySerImp.ListCategories().get(0).getNom();

        Assert.assertEquals("Vérifier la modification du nom ", newnom, nomupdated);
    }
    @Test
    public void TestSuppCategorie() {
        //String newnom = "testt";
        String newdes="";
        categorySerImp.SupprimerCategorie(categorySerImp.ListCategories().get(0).getId());
        //String nomupdated = categorySerImp.ListCategories().get(0).getNom();

        List<String> nomsCategories = categorySerImp.NomsCategories();
        Assert.assertTrue(nomsCategories.size() > 0);
    }

}