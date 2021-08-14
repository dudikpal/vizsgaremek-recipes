package training.vizsgaremekrecipes.creator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreatorCommand {

    @NotBlank(message = "creator name must be not empty")
    @Size(max = 255, message = "Creator name must be max 255 letter")
    @Schema(description = "name of the creator", example = "Stohl Judit")
    private String name;

    @Schema(description = "ssn of the creator", example = "117DNA")
    private String ssn;
}
