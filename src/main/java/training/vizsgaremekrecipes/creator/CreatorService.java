package training.vizsgaremekrecipes.creator;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training.vizsgaremekrecipes.recipe.CreateRecipeCommand;
import training.vizsgaremekrecipes.recipe.Recipe;
import training.vizsgaremekrecipes.recipe.RecipeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreatorService {

    private ModelMapper modelMapper;

    private CreatorRepository creatorRepository;

    private RecipeRepository recipeRepository;


    public List<CreatorDto> findAllCreator() {
        /*return creatorRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CreatorDto.class))
                .collect(Collectors.toList());*/
        return creatorRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CreatorDto.class))
                .collect(Collectors.toList());
    }


    public CreatorDto findCreatorById(long id) {
        return modelMapper.map(getCreatorById(id), CreatorDto.class);
    }

    private Creator getCreatorById(long id) {
        return creatorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("cannot find creator with id: " + id));
    }

    @Transactional
    public CreatorDto createCreator(CreateCreatorCommand command) {
        Creator creator = new Creator(command.getName(), command.getSsn());
        creatorRepository.save(creator);
        return modelMapper.map(creator, CreatorDto.class);
    }


    @Transactional
    public CreatorDto updateCreatorById(long id, UpdateCreatorCommand command) {
        Creator creator = getCreatorById(id);
        creator.setName(command.getName());
        creator.setSsn(command.getSsn());
        //creatorRepository.save(creator);
        return modelMapper.map(creator, CreatorDto.class);
    }

    public void deleteCreatorById(long id) {
        long idToDelete = getCreatorById(id).getId();
        creatorRepository.deleteById(idToDelete);
    }


    public Creator findCreatorByName(String name) {
        return creatorRepository.findCreatorByName(name).get();
    }

    @Transactional
    public CreatorDto addRecipe(long id, AddRecipeToCreatorCommand command) {
        Creator creator = getCreatorById(id);
        Recipe recipe = recipeRepository.getById(command.getRecipeId());
        creator.addRecipe(recipe);
        return modelMapper.map(creator, CreatorDto.class);
    }


    /*@Transactional
    public CreatorDto addRecipe(CreateCreatorCommand creatorCommand, CreateRecipeCommand recipeCommand) {
        Creator creator = creatorRepository.findCreatorByName(creatorCommand.getName())
                .orElseGet(() -> creatorRepository.save(
                        new Creator(creatorCommand.getName(), creatorCommand.getSsn())
                ));
        Recipe recipe = new Recipe(recipeCommand.getName(), recipeCommand.getDescription());
        creator.addRecipe(recipe);
        return modelMapper.map(creator, CreatorDto.class);
    }*/
}
