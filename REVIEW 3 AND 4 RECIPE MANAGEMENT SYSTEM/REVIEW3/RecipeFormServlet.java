package com.example.recipemanager.controller;

import com.example.recipemanager.repository.RecipeRepository;
import com.example.recipemanager.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class RecipeFormServlet extends HttpServlet {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/recipe-form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");

        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);

        try {
            recipeRepository.createRecipe(recipe);
            response.sendRedirect("recipes");
        } catch (SQLException e) {
            throw new ServletException("Error creating recipe", e);
        }
    }
}
