package training.vizsgaremekrecipes.ingredient;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@AllArgsConstructor
public class IngredientController {

    private IngredientService ingredientService;


    @GetMapping
    public List<IngredientDto> findAllIngredient() {
        return ingredientService.findAllIngredient();
    }


    @GetMapping("/{id}")
    public IngredientDto findIngredientById(@PathVariable long id) {
        return ingredientService.findIngredientById(id);
    }


    @PostMapping
    public IngredientDto createIngredient(@RequestBody CreateIngredientCommand command) {
        return ingredientService.createIngredient(command);
    }


    @PutMapping("/{id}")
    public IngredientDto updateIngredient(@PathVariable long id,  @RequestBody UpdateIngredientCommand command) {
        return ingredientService.updateIngredient(id, command);
    }


    @DeleteMapping("/{id}")
    public void deleteIngredientById(@PathVariable long id) {
        ingredientService.deleteIngredientById(id);
    }
}
