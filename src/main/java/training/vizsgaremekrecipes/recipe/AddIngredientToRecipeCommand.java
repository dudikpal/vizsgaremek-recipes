package training.vizsgaremekrecipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIngredientToRecipeCommand {

    private Long ingredientId;
}
