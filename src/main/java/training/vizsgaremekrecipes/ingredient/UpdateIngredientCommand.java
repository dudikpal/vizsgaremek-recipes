package training.vizsgaremekrecipes.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIngredientCommand {

    @NotBlank(message = "ingredient name must be not empty")
    @Size(max = 255)
    @Schema(description = "name of the ingredient", example = "Fokhagyma")
    private String name;

    @NotNull(message = "ingredient type must be not empty")
    @Schema(description = "quantity type", example = "GR")
    private QuantityType type;

    @NotNull(message = "ingredient quantity must be not null")
    @Positive(message = "ingredient quantity must be not negative or zero")
    @Schema(description = "quantity of the ingredient", example = "20")
    private int quantity;
}
