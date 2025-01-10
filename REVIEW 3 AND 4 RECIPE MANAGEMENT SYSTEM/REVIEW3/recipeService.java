package service;

import dao.RecipeDAO;
import model.Recipe;

import java.sql.SQLException;
import java.util.List;

public class RecipeService {
    private RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        return recipeDAO.getAllRecipes();
    }

    public boolean addRecipe(Recipe recipe) throws SQLException {
        return recipeDAO.addRecipe(recipe);
    }

    public boolean updateRecipe(Recipe recipe) throws SQLException {
        return recipeDAO.updateRecipe(recipe);
    }

    public boolean deleteRecipe(int id) throws SQLException {
        return recipeDAO.deleteRecipe(id);
    }

    public Recipe getRecipeById(int id) throws SQLException {
        return recipeDAO.getRecipeById(id);
    }
}
