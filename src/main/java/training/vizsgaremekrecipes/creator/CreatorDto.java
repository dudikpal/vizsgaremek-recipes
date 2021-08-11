package training.vizsgaremekrecipes.creator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.vizsgaremekrecipes.recipe.RecipeDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorDto {

    private long id;

    private String name;

    private String ssn;

    private List<RecipeDto> recipes;

}
