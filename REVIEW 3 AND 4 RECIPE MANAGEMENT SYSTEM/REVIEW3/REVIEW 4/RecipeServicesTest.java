package com.recipe.service;

import com.recipe.model.Recipe;
import com.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        recipeRepository = Mockito.mock(RecipeRepository.class);
        recipeService = new RecipeService(recipeRepository);
    }

    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe("Spaghetti", "Pasta, Tomato Sauce", "Boil pasta, add sauce.");
        when(recipeRepository.save(Mockito.any(Recipe.class))).thenReturn(recipe);
        Recipe savedRecipe = recipeService.addRecipe(recipe);
        assertEquals("Spaghetti", savedRecipe.getName());
    }

    @Test
    void testGetRecipeById() {
        Recipe recipe = new Recipe("Spaghetti", "Pasta, Tomato Sauce", "Boil pasta, add sauce.");
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        Optional<Recipe> foundRecipe = recipeService.getRecipeById(1L);
        assertEquals("Spaghetti", foundRecipe.get().getName());
    }
}
