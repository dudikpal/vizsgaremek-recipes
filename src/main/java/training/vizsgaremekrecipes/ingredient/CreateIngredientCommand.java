package training.vizsgaremekrecipes.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIngredientCommand {

    @NotBlank
    private String name;

    @NotNull
    private QuantityType type;

    @NotNull
    private int quantity;
}
