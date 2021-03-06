package training.vizsgaremekrecipes.ingredient;

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
@Sql(statements = "delete from ingredients")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class IngredientControllerTest {

    private String URL = "/api/ingredients";

    @Autowired
    TestRestTemplate template;

    @Test
    void find_All_Ingredient_Test() {
        template.postForObject(URL,
                new CreateIngredientCommand("ing1", QuantityType.DKG, 3),
                IngredientDto.class);
        template.postForObject(URL,
                new CreateIngredientCommand("ing2", QuantityType.DL, 4),
                IngredientDto.class);

        List<IngredientDto> ingredients = template.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IngredientDto>>() {
                }).getBody();

        assertThat(ingredients)
                .hasSize(2)
                .extracting(IngredientDto::getType)
                .containsExactly(QuantityType.DKG, QuantityType.DL);
    }


    @Test
    void find_Ingredient_By_Id_Test() {
        IngredientDto ingredient = template.postForObject(URL,
                new CreateIngredientCommand("ing1", QuantityType.DKG, 4),
                IngredientDto.class);

        long id = ingredient.getId();

        ingredient = template.getForObject(URL + "/" + id,
                IngredientDto.class);

        assertEquals("ing1", ingredient.getName());
    }


    @Test
    void update_Ingredient_By_Id_Test() {
        IngredientDto ingredient = template.postForObject(URL,
                new CreateIngredientCommand("ing1", QuantityType.DKG, 4),
                IngredientDto.class);

        long id = ingredient.getId();

        template.put(URL + "/" + id,
                new UpdateIngredientCommand("newIng2", QuantityType.DKG, 5));

        ingredient = template.getForObject(URL + "/" + id,
                IngredientDto.class);

        assertEquals("newIng2", ingredient.getName());
    }


    @Test
    void delete_Ingredient_By_Id_Test() {
        IngredientDto ingredient = template.postForObject(URL,
                new CreateIngredientCommand("ing1", QuantityType.DKG, 5),
                IngredientDto.class);

        long id = ingredient.getId();

        template.delete(URL + "/" + id);

        Problem problem = template.getForObject(URL + "/" + id,
                Problem.class);

        assertEquals(500, problem.getStatus().value());
    }


}
