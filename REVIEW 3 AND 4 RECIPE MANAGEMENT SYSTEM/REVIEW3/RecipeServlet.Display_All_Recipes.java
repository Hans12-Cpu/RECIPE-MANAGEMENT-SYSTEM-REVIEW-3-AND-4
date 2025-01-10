package servlet;

import dao.RecipeDAO;
import model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecipeServlet extends HttpServlet {
    private RecipeDAO recipeDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        recipeDAO = new RecipeDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Recipe> recipes = recipeDAO.getAllRecipes(); // Get all recipes from the database
            request.setAttribute("recipes", recipes); // Set recipes in request attribute
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/recipes.jsp");
            dispatcher.forward(request, response); // Forward the request to the JSP page
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving recipes.");
        }
    }
}
