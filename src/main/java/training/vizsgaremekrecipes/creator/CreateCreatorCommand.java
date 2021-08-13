package training.vizsgaremekrecipes.creator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreatorCommand {

    @NotBlank
    @Length(max = 255, message = "Creator name must be max 255 letter")
    private String name;

    private String ssn;
}
