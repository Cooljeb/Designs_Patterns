package fr.diginamic.hello.repositories;

import fr.diginamic.hello.entites.Ville;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <p>Repository pour la gestion des villes.</p>
 * @author Joseph BROU
 */
@Repository
public interface VilleRepository extends CrudRepository<Ville,Long> {
    // Ajouter des méthodes spécifiques à votre repository pour votre application

    /**Retourne l'ensemble des villes*/
    List<Ville>findBy();

    /**
     * Retourne une ville par son identifiant
     */
    Optional<Ville> findById(Long id);

    /**Retourne une ville par son nom*/
    Ville findByNom(String nom);

    /** teste l'existance de la ville dans la bdd*/
    boolean existsById(Long id);

    /** teste l'existance de la ville dans la bdd par le nom*/
    boolean existsByNom(String nom);

    /** recherche des villes les plus peulées*/
    @Query("SELECT v FROM Ville v WHERE v.departement.id = :codeDpt ORDER BY v.nbHabitants DESC")
    List<Ville>findByDepartementOrderByNbHabitants(@Param("codeDpt") String codeDpt, Pageable pageable);

    /**
     * Suppression depuis l'id passé en paramètre
     */

    void deleteById(Long id);

}
