package training.vizsgaremekrecipes.creator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    @Query("select c from Creator c where lower(c.name) = lower(:name)")
    Creator findCreatorByName(String name);
}
