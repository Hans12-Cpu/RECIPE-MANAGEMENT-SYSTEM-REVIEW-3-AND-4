package servlet;

import dao.RecipeDAO;
import model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddRecipeServlet extends HttpServlet {
    private RecipeDAO recipeDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        recipeDAO = new RecipeDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");
        String createdBy = request.getParameter("createdBy");

        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);
        recipe.setCreatedBy(createdBy);

        try {
            boolean success = recipeDAO.addRecipe(recipe); // Add recipe to the database
            if (success) {
                response.sendRedirect("recipes"); // Redirect to the list of recipes
            } else {
                request.setAttribute("error", "Failed to add the recipe.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addRecipe.jsp");
                dispatcher.forward(request, response); // Show the add recipe form again
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding the recipe.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addRecipe.jsp");
        dispatcher.forward(request, response); // Forward the request to the add recipe form
    }
}
