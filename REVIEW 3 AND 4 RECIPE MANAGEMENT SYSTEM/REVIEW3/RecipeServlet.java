package servlet;

import service.RecipeService;
import model.Recipe;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class RecipeServlet extends HttpServlet {
    private RecipeService recipeService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        recipeService = new RecipeService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recipe> recipes = null;
        try {
            recipes = recipeService.getAllRecipes();
            request.setAttribute("recipes", recipes);
            request.getRequestDispatcher("/jsp/recipeList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching recipes");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");

        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);

        try {
            boolean isAdded = recipeService.addRecipe(recipe);
            if (isAdded) {
                response.sendRedirect("recipes");
            } else {
                response.getWriter().println("Recipe addition failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error");
        }
    }
}
