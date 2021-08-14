package training.vizsgaremekrecipes.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeCommand {

    @NotBlank(message = "recipe name must be not empty")
    @Size(max = 50, message = "recipe name max siye is 50 characters")
    @Schema(description = "name of the recipe", example = "Hagymaleves")
    private String name;

    @NotBlank(message = "recipe description must be not empty")
    @Size(max = 2000, message = "recipe description max length is 2000 characters")
    @Schema(description = "instructions", example = """
                2. verzió
                A fokhagymát nagy kockákra, a vöröshagymát vastag karikákra vágjuk; a vajon és az olívaolajon lassú tűzön megdinszteljük.
                Meghintjük három evőkanál liszttel, sokszor megkeverjük, majd felöntjük forró húslevessel.
                Megfűszerezzük és forrás után még15 percig főzzük. (A hagymát ekkor botmixerrel pépesíthetjük, én azonban jobban szeretem hagymadarabokkal, így nálam ez a lépés elmarad.)
                A leveses tálkákba reszelt sajtot nem teszünk, erre merjük a levest, frissen őrölt borsot tekerünk rá, hozzá pirítóst tálalunk.""")
    private String description;
}
