package training.vizsgaremekrecipes.creator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    @Query("select c from Creator c where lower(c.name) = lower(:name)")
    Optional<Creator> findCreatorByName(String name);
}
