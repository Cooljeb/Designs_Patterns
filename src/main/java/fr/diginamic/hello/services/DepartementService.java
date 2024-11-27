package fr.diginamic.hello.services;

import fr.diginamic.hello.dao.DepartementDao;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartementService {
    @Autowired
    DepartementDao departementDao;

//    /**
//     * Initialisation des villes de départ pour la base de données
//     * utilsation de l'anotation "@PostConstruct"
//     */
//    @PostConstruct
//    @Transactional
//    public void init(){
//        villeDao.insertVille(new Ville("Paris", 22_000_000));
//        villeDao.insertVille(new Ville("London", 8_980_000));
//        villeDao.insertVille(new Ville("Berlin", 3_670_000));
//        villeDao.insertVille(new Ville("Madrid", 3_260_000));
//    }

    /**
     * Méthode pour récupérer les villes depuis la base de données.
     * via la couche d'accès aux données
     * @return Liste des villes
     */
    public List<Departement> extractDepartement(){
        return departementDao.extractDepartement();
    }

    /**
     * <p>Méthode pour récupérer un département à partir de son id
     * via la couche d'accès aux données </p>
     * <p> Si l'id est négatif, une exception est levée.</p>
     * @param id id de la ville
     * @return Ville
     */
    public Departement extractDepartement(Long id)  {

        return departementDao.extractDepartement(id);
    }

    /**
     * <p>Méthode pour récupérer un département à partir de son nom </p>
     * <p>Si le nom est vide, une exception est levée.</p>
     * @param nom
     * @return le département
     */
    public Departement extractDepartement(String nom) {
        return departementDao.extractDepartement(nom);
    }

    /**
     * <p>Méthode pour ajouter un département dans la base de données </p>
     * @param departement à ajouter
     * @return Liste des départements après ajout
     */
    public List<Departement> insertDepartement(Departement departement) {
        departementDao.insertDepartement(departement);
        return departementDao.extractDepartement();
    }


    /**
     * <p>Méthode pour modifier un département dans la base de données </p>
     * <p>On teste si le département existe et que la valeur n'est pas nulle avant de la modifier </p>
     * @param idDepartement
     * @param departementModifie
     * @return Liste des villes après modification
     */
    public List<Departement>modifierDepartement(Long idDepartement, Departement departementModifie) {
        Departement departementAModifier = departementDao.extractDepartement(idDepartement);
        if (departementAModifier!= null) {
            departementDao.modifierDepartement(departementAModifier.getId(), departementModifie);
        }
        return departementDao.extractDepartement();
    }

    /**
     * <p>Méthode pour supprimer un département dans la base de données </p>
     * <p>On teste si la ville existe et que la valeur n'est pas nulle avant de la supprimer </p>
     * @param idDepartement
     * @return Liste des villes après suppression
     */
    public List<Departement> supprimerDepartement(Long idDepartement) {
        Departement departementASupprimer = departementDao.extractDepartement(idDepartement);
        if (departementASupprimer!= null) {
            departementDao.supprimerDepartement(departementASupprimer.getId());
        }
        return departementDao.extractDepartement();
    }
}
