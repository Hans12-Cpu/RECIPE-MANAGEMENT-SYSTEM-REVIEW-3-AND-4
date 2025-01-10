public class RecipeService {

    private RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public boolean createRecipe(String title, String ingredients, String instructions, int userId) {
        Recipe recipe = new Recipe(title, ingredients, instructions, userId);
        return recipeDAO.addRecipe(recipe);
    }

    public Recipe getRecipe(int id) {
        return recipeDAO.getRecipeById(id);
    }
}
