package fr.epsi_26.bestioles.repository;

import fr.epsi_26.bestioles.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    // première espèce nom commun   findFirst + By + CommonName
    Species findFirstByCommonName(String commonName);

    // liste selon le nom    find + By + LatinName + Containing + IgnoreCase
    List<Species> findByLatinNameContainingIgnoreCase(String latinName);

    // TP5
    // nom commun ascendant
    @Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
    List<Species> findAllOrderedByCommonNameAsc();

    // LIKE sur nom commun
    @Query("SELECT s FROM Species s WHERE s.commonName LIKE :motif")
    List<Species> findByCommonNameLike(@Param("motif") String motif);
}