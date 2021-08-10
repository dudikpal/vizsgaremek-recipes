package training.vizsgaremekrecipes.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIngredientCommand {

    private String name;

    private QuantityType type;
}
