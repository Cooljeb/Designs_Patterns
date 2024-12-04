package fr.diginamic.hello.repositories;

import fr.diginamic.hello.entites.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long>, JpaRepository<Departement, Long> {
    // Ajouter des méthodes spécifiques à votre repository pour votre application

    /** Retourne l'ensemble des départements */
    List<Departement> findBy();

    /** Retourne une page de départements */
    Page<Departement> findAll(Pageable pageable);

    /** Retourne un département par son identifiant */
    Optional<Departement> findById(Long id);

    /** Retourne un département par son code */
    Optional<Departement> findByCode(String code);

    /** Retourne un département par son nom */
    Optional<Departement> findByNom(String nom);

    /** Teste l'existence d'un département par son ID */
    boolean existsById(Long id);

    /** Teste l'existence d'un département par son code */
    boolean existsByCode(String code);

    /** Teste l'existence d'un département par son nom */
    boolean existsByNom(String nom);

    /** Recherche des départements contenant un mot spécifique dans leur nom */
    List<Departement> findByNomContainingIgnoreCase(String mot);

    /** Retourne les départements avec le plus grand nombre de villes */
    @Query("SELECT d FROM Departement d JOIN d.villes v GROUP BY d.id ORDER BY COUNT(v.id) DESC")
    List<Departement> findByOrderByNombreVilles(Pageable pageable);

    /** Retourne les départements ayant une population totale supérieure à une valeur donnée */
    @Query("SELECT d FROM Departement d WHERE (SELECT SUM(v.nbHabitants) FROM Ville v WHERE v.departement.id = d.id) > :minPopulation")
    List<Departement> findByPopulationTotaleGreaterThan(@Param("minPopulation") Long minPopulation, Pageable pageable);

    /** Suppression d’un département par son ID */
    void deleteById(Long id);
}