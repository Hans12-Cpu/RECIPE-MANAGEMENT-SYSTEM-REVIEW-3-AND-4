import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RecipeDAOTest {

    private RecipeDAO recipeDAO;

    @BeforeEach
    public void setUp() {
        // Initialize RecipeDAO before each test
        recipeDAO = new RecipeDAO();
    }

    @Test
    public void testGetAllRecipes() {
        List<Recipe> recipes = recipeDAO.getAllRecipes();
        assertNotNull(recipes);  // Ensure the list is not null
        assertTrue(recipes.size() > 0);  // Ensure there are recipes in the database
    }

    @Test
    public void testAddRecipe() {
        Recipe recipe = new Recipe("Test Recipe", "Test Ingredients", "Test Instructions", 1);
        boolean result = recipeDAO.addRecipe(recipe);
        assertTrue(result);  // Ensure the recipe was added successfully
    }

    @Test
    public void testGetRecipeById() {
        Recipe recipe = recipeDAO.getRecipeById(1);
        assertNotNull(recipe);  // Ensure the recipe exists
        assertEquals("Pasta Carbonara", recipe.getTitle());  // Ensure the correct recipe is retrieved
    }

    @Test
    public void testDeleteRecipe() {
        boolean result = recipeDAO.deleteRecipe(1);
        assertTrue(result);  // Ensure the recipe was deleted successfully
    }
}
