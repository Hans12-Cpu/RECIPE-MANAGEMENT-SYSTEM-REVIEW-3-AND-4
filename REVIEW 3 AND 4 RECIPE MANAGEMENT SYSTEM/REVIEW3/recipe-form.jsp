<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create a New Recipe</title>
    <!-- Add CSS for basic styling (you can modify this according to your needs) -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #4CAF50;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin: 8px 0;
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        textarea {
            height: 150px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <h1>Create a New Recipe</h1>
    <p>Please fill in the details for the new recipe.</p>

    <!-- Form to submit new recipe -->
    <form action="recipe-form" method="post">
        <!-- Recipe Name -->
        <label for="name">Recipe Name:</label>
        <input type="text" id="name" name="name" required placeholder="Enter recipe name">

        <!-- Ingredients -->
        <label for="ingredients">Ingredients:</label>
        <textarea id="ingredients" name="ingredients" required placeholder="Enter ingredients (separate by commas)"></textarea>

        <!-- Instructions -->
        <label for="instructions">Instructions:</label>
        <textarea id="instructions" name="instructions" required placeholder="Enter recipe instructions"></textarea>

        <!-- Error Message Handling (if any) -->
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <!-- Submit Button -->
        <button type="submit">Submit Recipe</button>
    </form>

    <p><a href="recipes">Back to Recipe List</a></p>
</body>
</html>
