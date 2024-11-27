package fr.diginamic.hello.dao;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DepartementDao {
    @PersistenceContext
    private EntityManager em;


    /**
     * <p>Méthode permettant d'extraire toutes les villes
     * de la base de données.</p>
     * @return Liste des villes
     */
    @Transactional
    public List<Departement> extractDepartement(){
        return em.createQuery("SELECT d FROM Departement d", Departement.class).getResultList();
    }

    /**
     * Méthode permettant d'extraire une ville par son identifiant.
     * @param id identifiant de la ville à rechercher
     * @return la ville trouvée
     */

    public Departement extractDepartement(Long id) {
        return em.find(Departement.class, id);
    }

    /**
     * Méthode permettant d'extraire une ville par son nom
     * @param nom de la ville à rechercher
     * @return la ville trouvée
     */

    public Departement extractDepartement(String nom) {
        return em.createQuery("SELECT d FROM Departement d WHERE d.nom = :nom", Departement.class)
                .setParameter("nom", nom)
                .getSingleResult();
    }

    /**
     * Méthode permettant d'ajouter un departement à la base de données.
     * @param departement correspond à l'objet Ville à ajouter
     * @return la liste des villes après ajout
     */
    @Transactional
    public List <Departement> insertDepartement(Departement departement) {
        em.persist(departement);
        return extractDepartement();
    }

    /**
     * Méthode permettant de modifier une ville dans la base de données.
     * @param idDepartement Id de la ville concernée par la modification
     * @param departementModifie modification à apporter à la ville derrière l'id
     * @return la liste des villes après modification
     */
    @Transactional
    public List<Departement>modifierDepartement(Long idDepartement, Departement departementModifie) {
        em.merge(departementModifie);
        return extractDepartement();
    }

    /**
     * Méthode permettant de supprimer une ville de la base de données.
     * @param idDepartement id de la ville concernée par la suppression
     * @return la liste des villes après suppression
     */
    @Transactional
    public List<Departement> supprimerDepartement(Long idDepartement) {
        Departement departementASupprimer = extractDepartement(idDepartement);
        if (departementASupprimer!= null) {
            em.remove(departementASupprimer);
        }
        return extractDepartement();
    }
}
