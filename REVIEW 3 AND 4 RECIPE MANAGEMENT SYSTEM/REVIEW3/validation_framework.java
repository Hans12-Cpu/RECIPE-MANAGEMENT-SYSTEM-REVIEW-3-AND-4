import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Recipe {
    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    private String ingredients;

    @NotNull
    private String instructions;

    // Getters and Setters
}

