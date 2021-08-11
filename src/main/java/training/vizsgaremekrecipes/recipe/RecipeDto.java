package training.vizsgaremekrecipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.vizsgaremekrecipes.creator.Creator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private long id;

    private String name;

    private String description;

    //private Creator creator;
}
