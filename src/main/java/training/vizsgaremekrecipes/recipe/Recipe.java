package training.vizsgaremekrecipes.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.vizsgaremekrecipes.creator.Creator;

import javax.persistence.*;

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

    /*@ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;*/


    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }


    /*public void addCreator(Creator creator) {
        setCreator(creator);
        creator.addRecipe(this);
    }*/

}
