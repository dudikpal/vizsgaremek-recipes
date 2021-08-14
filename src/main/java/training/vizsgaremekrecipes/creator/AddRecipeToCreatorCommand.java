package training.vizsgaremekrecipes.creator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRecipeToCreatorCommand {

    @NotNull(message = "recipe id must be not null")
    @Positive(message = "recipe id must be not negative or zero")
    @Schema(description = "add recipe to creator", example = "1")
    private Long recipeId;
}
