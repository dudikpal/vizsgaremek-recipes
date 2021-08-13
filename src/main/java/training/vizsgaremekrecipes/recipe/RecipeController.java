package training.vizsgaremekrecipes.recipe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training.vizsgaremekrecipes.creator.CreateCreatorCommand;
import training.vizsgaremekrecipes.ingredient.CreateIngredientCommand;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import java.util.List;

@RestController
@Table(name = "recipes")
@RequestMapping("/api/recipes")
@AllArgsConstructor
@Tag(name = "Operations of recipes")
public class RecipeController {

    private RecipeService recipeService;


    @GetMapping
    @Operation(summary = "gives back all recipe")
    public List<RecipeDto> getRecipes() {
        return recipeService.getRecipes();
    }


    @GetMapping("/{id}")
    @Operation(summary = "give back a recipe by id")
    @ApiResponse(responseCode = "404", description = "recipe not found")
    public RecipeDto findRecipeById(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a recipe")
    @ApiResponse(responseCode = "201", description = "recipe has been created")
    public RecipeDto createRecipe(@Valid @RequestBody CreateRecipeCommand command) {
        return recipeService.createRecipe(command);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "update a recipe")
    @ApiResponse(responseCode = "404", description = "recipe not found")
    public RecipeDto updateRecipe(@PathVariable("id") long id, @Valid @RequestBody UpdateRecipeCommand command) {
        return recipeService.updateRecipe(id, command);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete a recipe")
    @ApiResponse(responseCode = "404", description = "recipe not found")
    public void deleteRecipeById(@PathVariable long id) {
        recipeService.deleteRecipeById(id);
    }


    @PostMapping("/{id}/ingredients")
    public RecipeDto addIngredient(@PathVariable("id") Long id, @Valid @RequestBody AddIngredientToRecipeCommand command) {
        return recipeService.addIngredient(id, command);
    }

}
