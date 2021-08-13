package training.vizsgaremekrecipes.creator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training.vizsgaremekrecipes.recipe.CreateRecipeCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/creators")
@AllArgsConstructor
@Tag(name = "Operations on creator")
public class CreatorController {

    private CreatorService creatorService;


    @GetMapping
    @Operation(summary = "gives back all creator")
    public List<CreatorDto> findAllCreator() {
        return creatorService.findAllCreator();
    }


    @GetMapping("/{id}")
    @Operation(summary = "give back a creator")
    @ApiResponse(responseCode = "404", description = "creator not found")
    public CreatorDto findCreatorById(@Valid @PathVariable long id) {
        return creatorService.findCreatorById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a creator")
    @ApiResponse(responseCode = "201", description = "creator has been created")
    public CreatorDto createCreator(@Valid @RequestBody CreateCreatorCommand command) {
        return creatorService.createCreator(command);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "update a creator")
    @ApiResponse(responseCode = "404", description = "creator not found")
    public CreatorDto updateCreatorById(@PathVariable long id, @Valid @RequestBody UpdateCreatorCommand command) {
        return creatorService.updateCreatorById(id, command);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete a creator")
    @ApiResponse(responseCode = "404", description = "creator not found")
    public void deleteCreatorById(@PathVariable long id) {
        creatorService.deleteCreatorById(id);
    }


    @PostMapping("/{id}/recipes")
    @Operation(summary = "add recipe to creator")
    @ApiResponse(responseCode = "201", description = "recipe has been added to creator")
    public CreatorDto addRecipe(@PathVariable("id") long id,
                                @RequestBody AddRecipeToCreatorCommand command) {
        return creatorService.addRecipe(id, command);
    }


    /*@PostMapping("/recipes")
    public CreatorDto addRecipe(@RequestBody CreateCreatorCommand creatorCommand,
                                @RequestBody CreateRecipeCommand recipecommand) {
        return creatorService.addRecipe(creatorCommand, recipecommand);
    }*/
}

