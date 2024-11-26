package fr.diginamic.hello.dao;

import fr.diginamic.hello.entites.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Classe DAO pour la gestion des villes
 * au niveau de la base de données.</p>
 * @author Joseph BROU
 */
@Service
public class VilleDao {
    /**
     * Déclaration de l'Entity Manager managé par Spring.
     */
    @PersistenceContext
    private EntityManager em;


    /**
     * <p>Méthode permettant d'extraire toutes les villes
     * de la base de données.</p>
     * @return Liste des villes
     */
    @Transactional
    public List<Ville> extractVille(){
        return em.createQuery("SELECT v FROM Ville v", Ville.class).getResultList();
    }

    /**
     * Méthode permettant d'extraire une ville par son identifiant.
     * @param id identifiant de la ville à rechercher
     * @return la ville trouvée
     */
    @Transactional
    public Ville extractVille(int id) {
        return em.find(Ville.class, id);
    }

    /**
     * Méthode permettant d'extraire une ville par son nom
     * @param nom de la ville à rechercher
     * @return la ville trouvée
     */
    @Transactional
    public Ville extractVille(String nom) {
        return em.createQuery("SELECT v FROM Ville v WHERE v.nom = :nom", Ville.class)
               .setParameter("nom", nom)
               .getSingleResult();
    }

    /**
     * Méthode permettant d'ajouter une ville à la base de données.
     * @param ville correspond à l'objet Ville à ajouter
     * @return la liste des villes après ajout
     */
    @Transactional
    public List <Ville> insertVille(Ville ville) {
        em.persist(ville);
        return extractVille();
    }

    /**
     * Méthode permettant de modifier une ville dans la base de données.
     * @param idVille Id de la ville concernée par la modification
     * @param villeModifiee modification à apporter à la ville derrière l'id
     * @return la liste des villes après modification
     */
    @Transactional
    public List<Ville>modifierVille(int idVille, Ville villeModifiee) {
        em.merge(villeModifiee);
        return extractVille();
    }

    /**
     * Méthode permettant de supprimer une ville de la base de données.
     * @param idVille id de la ville concernée par la suppression
     * @return la liste des villes après suppression
     */
    @Transactional
    public List<Ville> supprimerVille(int idVille) {
        Ville villeASupprimer = extractVille(idVille);
        if (villeASupprimer!= null) {
            em.remove(villeASupprimer);
        }
        return extractVille();
    }

}
