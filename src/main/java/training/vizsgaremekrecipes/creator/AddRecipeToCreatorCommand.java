package training.vizsgaremekrecipes.creator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRecipeToCreatorCommand {

    @NotNull
    private Long recipeId;
}
