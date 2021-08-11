package training.vizsgaremekrecipes.creator;

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
@Sql(statements = "delete from recipes; delete from creators;")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreatorControllerTest {

    private String URL = "/api/creators";

    @Autowired
    TestRestTemplate template;

    @Test
    void find_All_Creator_Test() {
        template.postForObject(URL,
                new CreateCreatorCommand("creator1", "ssn1"),
                CreatorDto.class);
        template.postForObject(URL,
                new CreateCreatorCommand("creator2", "ssn2"),
                CreatorDto.class);

        List<CreatorDto> creators = template.exchange(URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CreatorDto>>() {
                }).getBody();

        assertThat(creators)
                .hasSize(2)
                .extracting(CreatorDto::getSsn)
                .containsExactly("ssn1", "ssn2");
    }


    @Test
    void find_Creator_By_Id_Test() {
        CreatorDto creator = template.postForObject(URL,
                new CreateCreatorCommand("creator1", "ssn1"),
                CreatorDto.class);

        long id = creator.getId();

        creator = template.getForObject(URL + "/" + id,
                CreatorDto.class);

        assertEquals("creator1", creator.getName());
    }


    @Test
    void update_Creator_By_Id_Test() {
        CreatorDto creator = template.postForObject(URL,
                new CreateCreatorCommand("creator1", "ssn1"),
                CreatorDto.class);

        long id = creator.getId();

        template.put(URL + "/" + id,
                new UpdateCreatorCommand("creator1", "ssn777"));

        creator = template.getForObject(URL + "/" + id,
                CreatorDto.class);

        assertEquals("ssn777", creator.getSsn());
    }


    @Test
    void delete_Creator_By_Id_Test() {
        CreatorDto creator = template.postForObject(URL,
                new CreateCreatorCommand("creator1", "ssn1"),
                CreatorDto.class);

        long id = creator.getId();

        template.delete(URL + "/" + id);

        Problem problem = template.getForObject(URL + "/" + id,
                Problem.class);

        assertEquals(500, problem.getStatus().value());
    }

}