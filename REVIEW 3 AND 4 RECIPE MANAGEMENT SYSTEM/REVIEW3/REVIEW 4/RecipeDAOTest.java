import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeDAOTest {

    private RecipeDAO recipeDAO;

    @BeforeEach
    public void setUp() {
        // Initialize RecipeDAO
        recipeDAO = new RecipeDAO();
    }

    @Test
    public void testAddRecipe() {
        Recipe recipe = new Recipe("Spaghetti Carbonara", "Spaghetti, Eggs, Pancetta", "Cook pasta, mix with eggs and pancetta", 1);
        boolean result = recipeDAO.addRecipe(recipe);
        assertTrue(result, "Recipe should be added successfully.");
    }

    @Test
    public void testGetRecipeById() {
        // Assuming you already added a recipe with ID 1.
        Recipe recipe = recipeDAO.getRecipeById(1);
        assertNotNull(recipe, "Recipe should not be null");
        assertEquals("Spaghetti Carbonara", recipe.getTitle(), "Recipe title should match.");
    }
}
