package training.vizsgaremekrecipes.recipe;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from recipes")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RecipeControllerTest {
    
    private String URL = "/api/recipes";
    
    @Autowired
    TestRestTemplate template;

    @Test
    void find_All_Recipe_Test() {
        template.postForObject(URL,
                new CreateRecipeCommand("recipe1", "description1"),
                RecipeDto.class);
        template.postForObject(URL,
                new CreateRecipeCommand("recipe2", "description2"),
                RecipeDto.class);

        List<RecipeDto> recipes = template.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RecipeDto>>() {
                }).getBody();

        assertThat(recipes)
                .hasSize(2)
                .extracting(RecipeDto::getDescription)
                .containsExactly("description1", "description2");
    }


    @Test
    void find_Recipe_By_Id_Test() {
        RecipeDto recipe = template.postForObject(URL,
                new CreateRecipeCommand("recipe1", "description1"),
                RecipeDto.class);

        long id = recipe.getId();

        recipe = template.getForObject(URL + "/" + id,
                RecipeDto.class);

        assertEquals("recipe1", recipe.getName());
    }


    @Test
    void update_Recipe_By_Id_Test() {
        RecipeDto recipe = template.postForObject(URL,
                new CreateRecipeCommand("recipe1", "description1"),
                RecipeDto.class);

        long id = recipe.getId();

        template.put(URL + "/" + id,
                new UpdateRecipeCommand("recipe1", "newDesc1"));

        recipe = template.getForObject(URL + "/" + id,
                RecipeDto.class);

        assertEquals("newDesc1", recipe.getDescription());
    }


    @Test
    void delete_Recipe_By_Id_Test() {
        RecipeDto recipe = template.postForObject(URL,
                new CreateRecipeCommand("recipe1", "description1"),
                RecipeDto.class);

        long id = recipe.getId();

        template.delete(URL + "/" + id);

        Problem problem = template.getForObject(URL + "/" + id,
                Problem.class);

        assertEquals(500, problem.getStatus().value());
    }

}