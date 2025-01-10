import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class RecipeIntegrationTest {

    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        // Setup in-memory H2 database for testing
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        jdbcTemplate = new JdbcTemplate(dataSource);
        
        // Create schema and insert test data
        jdbcTemplate.execute("CREATE TABLE users (id INT PRIMARY KEY, username VARCHAR(50), password VARCHAR(50), email VARCHAR(100))");
        jdbcTemplate.execute("INSERT INTO users (id, username, password, email) VALUES (1, 'john_doe', 'password123', 'john@example.com')");
        jdbcTemplate.execute("CREATE TABLE recipes (id INT PRIMARY KEY, title VARCHAR(100), ingredients TEXT, instructions TEXT, user_id INT, FOREIGN KEY (user_id) REFERENCES users(id))");
    }

    @Test
    public void testDatabaseConnection() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        assertEquals(1, count);  // Ensure there is one user in the database
    }

    @Test
    public void testAddRecipe() {
        jdbcTemplate.execute("INSERT INTO recipes (id, title, ingredients, instructions, user_id) VALUES (1, 'Pasta Carbonara', 'Pasta, Eggs, Bacon', 'Boil pasta, cook bacon, mix ingredients', 1)");
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM recipes", Integer.class);
        assertEquals(1, count);  // Ensure the recipe was inserted into the database
    }
}
