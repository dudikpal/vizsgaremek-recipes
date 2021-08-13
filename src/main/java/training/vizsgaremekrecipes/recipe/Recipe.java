package training.vizsgaremekrecipes.recipe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import training.vizsgaremekrecipes.creator.Creator;
import training.vizsgaremekrecipes.ingredient.Ingredient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonBackReference
    private Creator creator;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Ingredient> ingredients;


    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }




}
