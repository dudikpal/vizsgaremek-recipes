package training.vizsgaremekrecipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.vizsgaremekrecipes.ingredient.Ingredient;
import training.vizsgaremekrecipes.ingredient.IngredientDto;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private long id;

    private String name;

    private String description;

    private List<IngredientDto> ingredients;
}
