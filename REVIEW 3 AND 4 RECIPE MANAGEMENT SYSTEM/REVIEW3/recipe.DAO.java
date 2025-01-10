package dao;

import model.Recipe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    private Connection connection; // Database connection object

    // Constructor to initialize the connection
    public RecipeDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new recipe to the database
    public boolean addRecipe(Recipe recipe) throws SQLException {
        String query = "INSERT INTO recipes (name, ingredients, instructions, created_by) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, recipe.getName()); // Set recipe name
            ps.setString(2, recipe.getIngredients()); // Set ingredients
            ps.setString(3, recipe.getInstructions()); // Set instructions
            ps.setString(4, recipe.getCreatedBy()); // Set the creator of the recipe

            // Execute the insert statement and return true if one row is affected
            return ps.executeUpdate() > 0;
        }
    }

    // Method to update an existing recipe in the database
    public boolean updateRecipe(Recipe recipe) throws SQLException {
        String query = "UPDATE recipes SET name = ?, ingredients = ?, instructions = ?, created_by = ? WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, recipe.getName()); // Set updated recipe name
            ps.setString(2, recipe.getIngredients()); // Set updated ingredients
            ps.setString(3, recipe.getInstructions()); // Set updated instructions
            ps.setString(4, recipe.getCreatedBy()); // Set updated creator
            ps.setInt(5, recipe.getId()); // Set the recipe id (for updating specific recipe)

            // Execute the update statement and return true if one row is affected
            return ps.executeUpdate() > 0;
        }
    }

    // Method to delete a recipe from the database
    public boolean deleteRecipe(int id) throws SQLException {
        String query = "DELETE FROM recipes WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id); // Set the recipe id to delete

            // Execute the delete statement and return true if one row is affected
            return ps.executeUpdate() > 0;
        }
    }

    // Method to retrieve a recipe by its ID from the database
    public Recipe getRecipeById(int id) throws SQLException {
        String query = "SELECT * FROM recipes WHERE id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id); // Set the recipe id to retrieve

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Create a new Recipe object and populate it with data from the ResultSet
                    Recipe recipe = new Recipe();
                    recipe.setId(rs.getInt("id"));
                    recipe.setName(rs.getString("name"));
                    recipe.setIngredients(rs.getString("ingredients"));
                    recipe.setInstructions(rs.getString("instructions"));
                    recipe.setCreatedBy(rs.getString("created_by"));
                    return recipe; // Return the Recipe object
                }
            }
        }
        return null; // Return null if no recipe is found
    }

    // Method to retrieve all recipes from the database
    public List<Recipe> getAllRecipes() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM recipes";
        
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Create a new Recipe object and populate it with data from the ResultSet
                Recipe recipe = new Recipe();
                recipe.setId(rs.getInt("id"));
                recipe.setName(rs.getString("name"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setInstructions(rs.getString("instructions"));
                recipe.setCreatedBy(rs.getString("created_by"));
                recipes.add(recipe); // Add the Recipe to the list
            }
        }
        return recipes; // Return the list of recipes
    }

    // Method to retrieve all recipes created by a specific user
    public List<Recipe> getRecipesByUser(String createdBy) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM recipes WHERE created_by = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, createdBy); // Set the creator's username to filter by

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Create a new Recipe object and populate it with data from the ResultSet
                    Recipe recipe = new Recipe();
                    recipe.setId(rs.getInt("id"));
                    recipe.setName(rs.getString("name"));
                    recipe.setIngredients(rs.getString("ingredients"));
                    recipe.setInstructions(rs.getString("instructions"));
                    recipe.setCreatedBy(rs.getString("created_by"));
                    recipes.add(recipe); // Add the Recipe to the list
                }
            }
        }
        return recipes; // Return the list of recipes created by the specific user
    }
}
