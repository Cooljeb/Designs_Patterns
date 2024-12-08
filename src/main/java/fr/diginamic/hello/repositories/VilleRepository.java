package fr.diginamic.hello.repositories;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * <p>Repository pour la gestion des villes.</p>
 * @author Joseph BROU
 */
@Repository
public interface VilleRepository extends CrudRepository<Ville,Long>, JpaRepository<Ville,Long> {
    // Ajouter des méthodes spécifiques à votre repository pour votre application

    /**Retourne l'ensemble des villes*/
    List<Ville>findBy();

    Page<Ville> findAll(Pageable pageable);

    /**
     * Retourne une ville par son identifiant
     */
    Optional<Ville> findById(Long id);

    /**Retourne une ville par son nom*/
    Optional<Ville>  findByNom(String nom);

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
    /** Recherche de toutes les villes dont le nom commence par le mot passé en paramètre.*/
    List<Ville> findAllByNomStartingWith(String mot);

    /**Recherche de toutes les villes dont la population est supérieure à min (paramètre de type int)*/
    List<Ville> findAllByNbHabitantsGreaterThan(Integer min);

    /**Recherche de toutes les villes dont la population est supérieure à min et inférieure à max.*/
    List<Ville> findAllByNbHabitantsBetween(Integer min, Integer max);

    /**Recherche de toutes les villes d’un département dont la population est supérieure à min (paramètre de type int)*/
    List<Ville> findByDepartement_idAndNbHabitantsGreaterThan(Long departement_id, Integer nbHabitants);

    /**Recherche de toutes les villes d’un département dont la population est supérieure à min et inférieure à max.*/
    List<Ville> findByDepartement_idAndNbHabitantsBetween(Long departement_id, Integer min, Integer max);

    /**Recherche des n villes les plus peuplées d’un département donné (n est aussi un paramètre)**/
    List<Ville>findByDepartement_idOrderByNbHabitantsDesc(Long departement_id,Pageable pageable);

}
