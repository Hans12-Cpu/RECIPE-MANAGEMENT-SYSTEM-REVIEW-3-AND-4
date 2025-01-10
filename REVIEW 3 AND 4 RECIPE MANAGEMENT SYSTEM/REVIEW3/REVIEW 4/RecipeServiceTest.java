import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeServiceTest {

    private RecipeDAO recipeDAO;
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        // Create a mock for RecipeDAO
        recipeDAO = mock(RecipeDAO.class);
        // Initialize RecipeService with mocked DAO
        recipeService = new RecipeService(recipeDAO);
    }

    @Test
    public void testCreateRecipe() {
        Recipe mockRecipe = new Recipe("Pasta Carbonara", "Pasta, Eggs, Bacon", "Cook pasta, mix with eggs and bacon", 1);
        when(recipeDAO.addRecipe(mockRecipe)).thenReturn(true);

        boolean result = recipeService.createRecipe("Pasta Carbonara", "Pasta, Eggs, Bacon", "Cook pasta, mix with eggs and bacon", 1);
        assertTrue(result, "Recipe creation should be successful.");
        verify(recipeDAO, times(1)).addRecipe(mockRecipe);  // Verify addRecipe was called once
    }

    @Test
    public void testGetRecipe() {
        Recipe mockRecipe = new Recipe(1, "Pasta Carbonara", "Pasta, Eggs, Bacon", "Cook pasta, mix with eggs and bacon", 1);
        when(recipeDAO.getRecipeById(1)).thenReturn(mockRecipe);

        Recipe recipe = recipeService.getRecipe(1);
        assertNotNull(recipe, "Recipe should not be null.");
        assertEquals("Pasta Carbonara", recipe.getTitle(), "Recipe title should match.");
        verify(recipeDAO, times(1)).getRecipeById(1);  // Verify getRecipeById was called once
    }
}
