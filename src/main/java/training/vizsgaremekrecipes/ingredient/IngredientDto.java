package training.vizsgaremekrecipes.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

    private long id;

    private String name;

    private QuantityType type;

    private int quantity;

}
