<form action="addRecipe" method="POST" onsubmit="return validateForm()">
    <label for="title">Recipe Title</label>
    <input type="text" id="title" name="title" required>

    <label for="ingredients">Ingredients</label>
    <textarea id="ingredients" name="ingredients" required></textarea>

    <label for="instructions">Instructions</label>
    <textarea id="instructions" name="instructions" required></textarea>

    <input type="submit" value="Add Recipe">
</form>

<script>
    function validateForm() {
        const title = document.getElementById('title').value;
        const ingredients = document.getElementById('ingredients').value;
        const instructions = document.getElementById('instructions').value;

        if (title.trim() === "" || ingredients.trim() === "" || instructions.trim() === "") {
            alert("All fields are required.");
            return false;  // Prevent form submission
        }
        return true;  // Allow form submission
    }
</script>
