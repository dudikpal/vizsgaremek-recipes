package training.vizsgaremekrecipes.creator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreatorCommand {

    @Size(max = 50, message = "creator name max length is 50 letter")
    @Schema(description = "new name of the creator", example = "Stahl Buci")
    private String name;

    @Size(max = 20, message = "creator ssn max length is 20 character")
    @Schema(description = "new ssn of the creator", example = "987DDA")
    private String ssn;
}
