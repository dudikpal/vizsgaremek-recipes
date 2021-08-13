package training.vizsgaremekrecipes;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import training.vizsgaremekrecipes.creator.AddRecipeToCreatorCommand;
import training.vizsgaremekrecipes.creator.CreateCreatorCommand;
import training.vizsgaremekrecipes.creator.Creator;
import training.vizsgaremekrecipes.creator.CreatorDto;
import training.vizsgaremekrecipes.ingredient.CreateIngredientCommand;
import training.vizsgaremekrecipes.ingredient.Ingredient;
import training.vizsgaremekrecipes.ingredient.IngredientDto;
import training.vizsgaremekrecipes.ingredient.QuantityType;
import training.vizsgaremekrecipes.recipe.AddIngredientToRecipeCommand;
import training.vizsgaremekrecipes.recipe.CreateRecipeCommand;
import training.vizsgaremekrecipes.recipe.Recipe;
import training.vizsgaremekrecipes.recipe.RecipeDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql(statements = "delete from ingredients; delete from recipes; delete from creators;")
public class ApplicationIT {

    private String CREATOR_URL = "/api/creators";
    private String RECIPE_URL = "/api/recipes";
    private String INGREDIENTS_URL = "/api/ingredients";

    @Autowired
    TestRestTemplate template;

    @Test
    void create_Complet_Recipe_Test() {
        CreatorDto creator = template.postForObject(CREATOR_URL,
                new CreateCreatorCommand("creator 1", "789SSN"),
                CreatorDto.class);

        long creatorId = creator.getId();

        RecipeDto recipe = template.postForObject(RECIPE_URL,
                new CreateRecipeCommand("recipe 1", "instructions"),
                RecipeDto.class);

        long recipeId = recipe.getId();

        IngredientDto ingredient1 = template.postForObject(INGREDIENTS_URL,
                new CreateIngredientCommand("ingredient 1", QuantityType.CL, 15),
                IngredientDto.class);

        long ingredient1Id = ingredient1.getId();

        IngredientDto ingredient2 = template.postForObject(INGREDIENTS_URL,
                new CreateIngredientCommand("ingredient 2", QuantityType.GR, 150),
                IngredientDto.class);

        long ingredient2Id = ingredient2.getId();

        template.postForObject(CREATOR_URL + "/" + creatorId + "/recipes",
                new AddRecipeToCreatorCommand(recipeId),
                        CreatorDto.class);

        template.postForObject(RECIPE_URL + "/" + recipeId + "/ingredients",
                new AddIngredientToRecipeCommand(ingredient1Id),
                CreatorDto.class);

        template.postForObject(RECIPE_URL + "/" + recipeId + "/ingredients",
                new AddIngredientToRecipeCommand(ingredient2Id),
                CreatorDto.class);


        CreatorDto creatorFromDB = template.getForObject(CREATOR_URL + "/" + creatorId,
                CreatorDto.class);

        assertEquals("creator 1", creatorFromDB.getName());
        assertEquals("recipe 1", creatorFromDB.getRecipes().get(0).getName());
        assertEquals("ingredient 1", creatorFromDB.getRecipes().get(0).getIngredients().get(0).getName());

        assertThat(creatorFromDB.getRecipes().get(0).getIngredients())
                .hasSize(2)
                .extracting(IngredientDto::getQuantity)
                .containsExactly(15, 150);

    }


}
