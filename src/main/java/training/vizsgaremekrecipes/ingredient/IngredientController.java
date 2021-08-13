package training.vizsgaremekrecipes.ingredient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@AllArgsConstructor
@Tag(name = "Operations of ingredients")
public class IngredientController {

    private IngredientService ingredientService;


    @GetMapping
    @Operation(summary = "gives back all ingredient")
    public List<IngredientDto> findAllIngredient() {
        return ingredientService.findAllIngredient();
    }


    @GetMapping("/{id}")
    @Operation(summary = "give back an ingredient")
    @ApiResponse(responseCode = "404", description = "ingredient not found")
    public IngredientDto findIngredientById(@PathVariable long id) {
        return ingredientService.findIngredientById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create an ingredient")
    @ApiResponse(responseCode = "201", description = "ingredient has been created")
    public IngredientDto createIngredient(@Valid @RequestBody CreateIngredientCommand command) {
        return ingredientService.createIngredient(command);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "update ingredient")
    @ApiResponse(responseCode = "404", description = "ingredient not found")
    public IngredientDto updateIngredient(@PathVariable long id, @Valid @RequestBody UpdateIngredientCommand command) {
        return ingredientService.updateIngredient(id, command);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete ingredient by id")
    @ApiResponse(responseCode = "404", description = "ingredient not found")
    public void deleteIngredientById(@PathVariable long id) {
        ingredientService.deleteIngredientById(id);
    }
}
