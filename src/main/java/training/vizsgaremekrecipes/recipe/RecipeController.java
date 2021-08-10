package training.vizsgaremekrecipes.recipe;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@RestController
@Table(name = "recipes")
@RequestMapping("/api/recipes")
@AllArgsConstructor
public class RecipeController {

    private RecipeService recipeService;


    @GetMapping
    public List<RecipeDto> getRecipes() {
        return recipeService.getRecipes();
    }


    @GetMapping("/{id}")
    public RecipeDto findRecipeById(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }


    @PostMapping
    public RecipeDto createRecipe(@RequestBody CreateRecipeCommand command) {
        return recipeService.createRecipe(command);
    }


    @PutMapping("/{id}")
    public RecipeDto updateRecipe(@PathVariable("id") long id, @RequestBody UpdateRecipeCommand command) {
        return recipeService.updateRecipe(id, command);
    }


    @DeleteMapping("/{id}")
    public void deleteRecipeById(@PathVariable long id) {
        recipeService.deleteRecipeById(id);
    }
}
