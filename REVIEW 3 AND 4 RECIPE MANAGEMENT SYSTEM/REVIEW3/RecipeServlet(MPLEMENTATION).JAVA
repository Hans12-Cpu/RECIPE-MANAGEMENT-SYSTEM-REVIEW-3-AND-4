package com.example.recipemanager.controller;

import com.example.recipemanager.model.Recipe;
import com.example.recipemanager.dao.RecipeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {
    
    private RecipeDAO recipeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize DAO
        recipeDAO = new RecipeDAO();
    }

    // Handle GET request (retrieve and display recipes)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all recipes from the database using RecipeDAO
        List<Recipe> recipes = recipeDAO.getAllRecipes();
        
        // Set the list of recipes as an attribute for the JSP page
        request.setAttribute("recipes", recipes);
        
        // Forward the request to a JSP page to display the recipes
        request.getRequestDispatcher("/recipes.jsp").forward(request, response);
    }

    // Handle POST request (add a new recipe or update an existing one)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form (POST request)
        String recipeName = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");

        // Create a new Recipe object and populate it with data
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);

        // Add the new recipe to the database using RecipeDAO
        recipeDAO.addRecipe(recipe);

        // Redirect to the recipe list page after adding the recipe
        response.sendRedirect(request.getContextPath() + "/recipe");
    }

    // Clean up resources (if needed)
    @Override
    public void destroy() {
        super.destroy();
        // Cleanup code (close connections, etc.)
    }
}
