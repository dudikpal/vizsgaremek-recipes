package training.vizsgaremekrecipes.recipe;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training.vizsgaremekrecipes.creator.*;
import training.vizsgaremekrecipes.ingredient.CreateIngredientCommand;
import training.vizsgaremekrecipes.ingredient.Ingredient;
import training.vizsgaremekrecipes.ingredient.IngredientRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {

    private ModelMapper modelMapper;

    private RecipeRepository recipeRepository;

    private IngredientRepository ingredientRepository;


    public List<RecipeDto> getRecipes() {
        return recipeRepository.findAll().stream()
                .map(r -> modelMapper.map(r, RecipeDto.class))
                .collect(Collectors.toList());
    }

    public RecipeDto findRecipeById(long id) {
        return modelMapper.map(getRecipeById(id), RecipeDto.class);
    }

    private Recipe getRecipeById(long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("cannot find Recipe"));
    }

    public RecipeDto createRecipe(CreateRecipeCommand command) {
        Recipe recipe = new Recipe(command.getName(), command.getDescription());
        /*Creator creator = creatorService.findCreatorByName(command.getCreatorName());
        if (creator == null) {
            creator = new Creator(command.getCreatorName())
        }*/
        recipeRepository.save(recipe);
        return modelMapper.map(recipe, RecipeDto.class);
    }

    @Transactional
    public RecipeDto updateRecipe(long id, UpdateRecipeCommand command) {
        Recipe recipe = getRecipeById(id);
        recipe.setName(command.getName());
        recipe.setDescription(command.getDescription());
        return modelMapper.map(recipe, RecipeDto.class);
    }

    public void deleteRecipeById(long id) {
        recipeRepository.deleteById(id);
    }

    @Transactional
    public RecipeDto addIngredient(Long id, AddIngredientToRecipeCommand command) {
        Recipe recipe = getRecipeById(id);
        Ingredient ingredient = ingredientRepository.getById(command.getIngredientId());
        recipe.addIngredient(ingredient);
        return modelMapper.map(recipe, RecipeDto.class);
    }
}
