<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recipe List</title>
    <!-- Add CSS for styling (modify this according to your design preferences) -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #4CAF50;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .button {
            background-color: #4CAF50;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #45a049;
        }
        .delete-button {
            background-color: #f44336;
        }
        .delete-button:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>

    <h1>Recipe List</h1>
    <p>Below is a list of all recipes in the system. You can add, edit, or delete recipes.</p>

    <!-- Link to create a new recipe -->
    <a href="recipe-form" class="button">Add New Recipe</a>

    <!-- Display Recipe Table -->
    <table>
        <thead>
            <tr>
                <th>Recipe Name</th>
                <th>Ingredients</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate through the list of recipes passed from the servlet -->
            <c:forEach var="recipe" items="${recipes}">
                <tr>
                    <td>${recipe.name}</td>
                    <td>${recipe.ingredients}</td>
                    <td class="actions">
                        <!-- View Button (optional for detailed view) -->
                        <a href="recipe-detail?id=${recipe.id}" class="button">View</a>
                        
                        <!-- Edit Button -->
                        <a href="recipe-form?id=${recipe.id}" class="button">Edit</a>
                        
                        <!-- Delete Button -->
                        <a href="recipe-delete?id=${recipe.id}" class="button delete-button" onclick="return confirm('Are you sure you want to delete this recipe?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
