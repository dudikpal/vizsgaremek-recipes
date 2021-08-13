package training.vizsgaremekrecipes.creator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import training.vizsgaremekrecipes.recipe.Recipe;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creators")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ssn;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private List<Recipe> recipes;

    public Creator(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }


    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        recipe.setCreator(this);
    }

}
