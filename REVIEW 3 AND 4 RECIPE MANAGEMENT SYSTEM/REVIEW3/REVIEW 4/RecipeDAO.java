public class RecipeDAO {

    private Connection connection;

    public RecipeDAO() {
        // Connect to the database
        try {
            this.connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addRecipe(Recipe recipe) {
        String query = "INSERT INTO recipes (title, ingredients, instructions, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, recipe.getTitle());
            stmt.setString(2, recipe.getIngredients());
            stmt.setString(3, recipe.getInstructions());
            stmt.setInt(4, recipe.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Recipe getRecipeById(int id) {
        String query = "SELECT * FROM recipes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Recipe(resultSet.getInt("id"),
                                  resultSet.getString("title"),
                                  resultSet.getString("ingredients"),
                                  resultSet.getString("instructions"),
                                  resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add more methods (update, delete, etc.) as necessary
}
