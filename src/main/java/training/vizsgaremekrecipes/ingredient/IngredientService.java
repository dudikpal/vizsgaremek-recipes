package training.vizsgaremekrecipes.ingredient;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientService {

    private ModelMapper modelMapper;

    private IngredientRepository ingredientRepository;


    public List<IngredientDto> findAllIngredient() {
        return ingredientRepository.findAll()
                .stream()
                .map(i -> modelMapper.map(i, IngredientDto.class))
                .collect(Collectors.toList());
    }


    public IngredientDto findIngredientById(long id) {
        return modelMapper.map(getIngredientById(id), IngredientDto.class);
    }


    private Ingredient getIngredientById(long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find ingredient"));
    }


    public IngredientDto createIngredient(CreateIngredientCommand command) {
        Ingredient ingredient = new Ingredient(command.getName(), command.getType(), command.getQuantity());
        ingredientRepository.save(ingredient);
        return modelMapper.map(ingredient, IngredientDto.class);
    }

    @Transactional
    public IngredientDto updateIngredient(Long id, UpdateIngredientCommand command) {
        Ingredient ingredient = ingredientRepository.getById(id);
        ingredient.setName(command.getName());
        ingredient.setType(command.getType());
        //ingredientRepository.save(ingredient);
        return modelMapper.map(ingredient, IngredientDto.class);
    }

    public void deleteIngredientById(long id) {
        ingredientRepository.deleteById(id);
    }
}
