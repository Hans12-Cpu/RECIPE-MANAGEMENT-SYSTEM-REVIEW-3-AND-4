package com.example.recipemanager.repository;

import com.example.recipemanager.model.Recipe;
import com.example.recipemanager.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    
    // Create a new recipe
    public void createRecipe(Recipe recipe) throws SQLException {
        String query = "INSERT INTO recipes (name, ingredients, instructions) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, recipe.getName());
            stmt.setString(2, recipe.getIngredients());
            stmt.setString(3, recipe.getInstructions());
            stmt.executeUpdate();
        }
    }

    // Get all recipes
    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM recipes";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(rs.getLong("id"));
                recipe.setName(rs.getString("name"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setInstructions(rs.getString("instructions"));
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    // Get a single recipe by ID
    public Recipe getRecipeById(long id) throws SQLException {
        String query = "SELECT * FROM recipes WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setId(rs.getLong("id"));
                    recipe.setName(rs.getString("name"));
                    recipe.setIngredients(rs.getString("ingredients"));
                    recipe.setInstructions(rs.getString("instructions"));
                    return recipe;
                }
            }
        }
        return null;
    }

    // Delete a recipe by ID
    public void deleteRecipe(long id) throws SQLException {
        String query = "DELETE FROM recipes WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
