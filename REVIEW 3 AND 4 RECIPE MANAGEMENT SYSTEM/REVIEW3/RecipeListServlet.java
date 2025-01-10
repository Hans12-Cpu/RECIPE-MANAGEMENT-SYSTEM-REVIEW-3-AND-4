package com.example.recipemanager.controller;

import com.example.recipemanager.repository.RecipeRepository;
import com.example.recipemanager.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RecipeListServlet extends HttpServlet {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Recipe> recipes = recipeRepository.getAllRecipes();
            request.setAttribute("recipes", recipes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/recipes.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving recipes", e);
        }
    }
}
