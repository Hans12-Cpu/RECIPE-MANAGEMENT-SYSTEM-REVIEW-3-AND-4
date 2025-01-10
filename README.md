# **Recipe Management System: README**

## **Project Overview**

The **Recipe Management System** is a web application that allows users to register, log in, and manage their recipes. Users can add new recipes, view their list of recipes, and perform CRUD (Create, Read, Update, Delete) operations on them. This project is built using **Java**, **Servlets**, **JSP**, **MySQL**, and **Maven**.

---

## **Features**

- **User Registration**: Allows users to sign up with a username, password, and email.
- **Recipe Management**: Users can add, view, edit, and delete their recipes.
- **Responsive UI**: Built using **Bootstrap** to ensure a mobile-friendly, responsive interface.
- **JSP & Servlets**: The application follows the **MVC pattern** with JSP for views, Servlets for controllers, and JavaBeans for models.
- **Database Integration**: Data is stored in a MySQL database.
- **Form Validation**: Includes both client-side and server-side validation for input fields.

---

## **Technologies Used**

- **Java** (JDK 11 or higher)
- **Servlets & JSP**
- **JDBC** (Java Database Connectivity)
- **MySQL** for data storage
- **Maven** for project management
- **Bootstrap** for responsive design
- **JSTL** (JavaServer Pages Standard Tag Library)
- **JUnit** for unit testing

---

## **Installation and Setup**

### **Prerequisites**

Before setting up the project, ensure that you have the following installed:

- **Java Development Kit (JDK 11 or higher)**
- **Apache Maven** (for building and managing the project)
- **MySQL Database** (for storing the data)

### **Steps to Set Up the Project**

1. **Clone the Repository**

   Clone this repository to your local machine:

   ```bash
   git clone https://github.com/Hans12-Cpu/RECIPE-MANAGEMENT-SYSTEM-REVIEW-3-AND-4/recipe-management-system.git
   ```

2. **Install MySQL and Create Database**

   Install MySQL if not already installed. Then, create a new database and use the provided SQL schema to set up the necessary tables:

   ```sql
   CREATE DATABASE recipe_management;
   USE recipe_management;

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) NOT NULL,
       password VARCHAR(50) NOT NULL,
       email VARCHAR(100) NOT NULL
   );

   CREATE TABLE recipes (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(100) NOT NULL,
       ingredients TEXT NOT NULL,
       instructions TEXT NOT NULL,
       user_id INT NOT NULL,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   ```

3. **Configure Database Connection**

   In the `DatabaseUtil.java` file, update the database connection settings with your local MySQL configuration (e.g., username, password, and database URL):

   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/recipe_management";
   private static final String USER = "root";
   private static final String PASSWORD = "your-password";
   ```

4. **Build the Project**

   Navigate to the root folder of the project in your terminal and build the project using Maven:

   ```bash
   mvn clean install
   ```

5. **Run the Application**

   After building the project, you can run it using Maven. The application uses **Jetty** as the web server.

   ```bash
   mvn jetty:run
   ```

   The application will be available at: `http://localhost:8080/recipe-management-system/`

---

## **Usage**

### **User Registration**
1. Navigate to the `register.jsp` page to create an account.
2. Fill in the username, email, and password fields.
3. Submit the form to register a new user.

### **Manage Recipes**
1. After logging in, you can add a new recipe by navigating to the recipe creation form.
2. You can also view the list of your recipes, edit existing ones, or delete them.

---

## **Project Structure**

```plaintext
recipe-management-system
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── recipe/
│   │   │   │       ├── dao/
│   │   │   │       │   └── RecipeDAO.java
│   │   │   │       ├── model/
│   │   │   │       │   └── Recipe.java
│   │   │   │       ├── servlet/
│   │   │   │       │   └── RecipeServlet.java
│   │   │   │       ├── service/
│   │   │   │       │   └── RecipeService.java
│   │   │   │       └── util/
│   │   │   │           └── DatabaseUtil.java
│   │   └── resources/
│   │       └── application.properties
│   ├── webapp/
│   │   ├── WEB-INF/
│   │   │   ├── web.xml
│   │   ├── index.jsp
│   │   ├── register.jsp
│   │   └── recipes.jsp
│   └── test/
│       ├── java/
│       │   └── com/recipe/
│       │       └── service/
│       │           └── RecipeServiceTest.java
│       └── resources/
└── pom.xml
```

- **DAO Layer**: Contains classes like `RecipeDAO.java` for handling database operations.
- **Service Layer**: Includes the `RecipeService.java` class for business logic.
- **Servlets**: Serves as controllers for handling HTTP requests. Example: `RecipeServlet.java`.
- **JSP Pages**: Views for user interaction. Example: `register.jsp` and `recipes.jsp`.

---

## **Unit Testing**

Unit tests are created to validate the functionality of the **DAO** and **Service** layers. The unit tests are located in the `test` folder.

Example unit test for the service layer:

```java
@Test
public void testAddRecipe() {
    Recipe recipe = new Recipe("Spaghetti", "Pasta, Tomato Sauce", "Boil pasta, cook sauce", 1);
    boolean result = recipeDAO.addRecipe(recipe);
    assertTrue(result, "Recipe should be added successfully");
}
```

To run tests:

```bash
mvn test
```

---

## **Contributing**

1. Fork the repository.
2. Create a new branch for your feature.
3. Make your changes and commit them.
4. Push to your forked repository.
5. Open a pull request to the main repository.

---

## **License**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## **Acknowledgements**

- **Bootstrap**: For providing the responsive UI framework.
- **MySQL**: For being the database solution.
- **JUnit**: For enabling unit testing in Java.
- **Maven**: For managing project dependencies and builds.

--- 

Thank you for exploring the Recipe Management System!
