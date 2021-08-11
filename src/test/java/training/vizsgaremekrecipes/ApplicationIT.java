package training.vizsgaremekrecipes;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import training.vizsgaremekrecipes.creator.CreateCreatorCommand;
import training.vizsgaremekrecipes.creator.Creator;
import training.vizsgaremekrecipes.creator.CreatorDto;
import training.vizsgaremekrecipes.recipe.CreateRecipeCommand;
import training.vizsgaremekrecipes.recipe.RecipeDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql(statements = "delete from creators")
public class ApplicationIT {

    private String CREATOR_URL = "/api/creators";
    private String RECIPE_URL = "/api/recipes";
    private String INGREDIENTS_URL = "/api/ingredients";

    @Autowired
    TestRestTemplate template;

    @Test
    void add_Recipe_To_Creator_Test() {
        CreatorDto creator = template.postForObject(CREATOR_URL,
                new CreateCreatorCommand("creator 1", "789SSN"),
                CreatorDto.class);

        long id = creator.getId();

        creator = template.postForObject(CREATOR_URL + "/" + id + "/recipes",
                new CreateRecipeCommand("recipe 1", "instructions"),
                        CreatorDto.class);

        CreatorDto creatorFromDB = template.getForObject(CREATOR_URL + "/" + id,
                CreatorDto.class);

        /*assertEquals(1, creatorFromDB.getRecipes().size());
        assertEquals("recipe 1", creatorFromDB.getRecipes().get(0).getName());*/

        assertThat(creatorFromDB.getRecipes())
                .hasSize(1)
                .extracting(RecipeDto::getDescription)
                .containsExactly("instructions");
    }
}
