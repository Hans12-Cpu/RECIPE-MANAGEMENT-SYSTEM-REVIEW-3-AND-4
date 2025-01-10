package com.example.recipemanager.controller;

import com.example.recipemanager.repository.RecipeRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class RecipeDeleteServlet extends HttpServlet {
    private final RecipeRepository recipeRepository = new RecipeRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long recipeId = Long.parseLong(request.getParameter("id"));

        try {
            recipeRepository.deleteRecipe(recipeId);
            response.sendRedirect("recipes");
        } catch (SQLException e) {
            throw new ServletException("Error deleting recipe", e);
        }
    }
}
