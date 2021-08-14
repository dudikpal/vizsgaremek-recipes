package training.vizsgaremekrecipes.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIngredientToRecipeCommand {

    @NotNull(message = "ingredient id must be not null")
    @Positive(message = "ingredient id must be not negative or zero")
    @Schema(description = "add ingredient to recipe", example = "1")
    private Long ingredientId;
}
