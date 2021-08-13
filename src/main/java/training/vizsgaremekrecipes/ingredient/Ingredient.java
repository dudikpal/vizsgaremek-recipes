package training.vizsgaremekrecipes.ingredient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.vizsgaremekrecipes.recipe.Recipe;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private QuantityType type;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;


    public Ingredient(String name, QuantityType type, int quantity) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }
}
