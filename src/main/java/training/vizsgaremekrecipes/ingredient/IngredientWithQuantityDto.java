package training.vizsgaremekrecipes.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientWithQuantityDto {

    private IngredientDto ingredient;

    private int quantity;
}
