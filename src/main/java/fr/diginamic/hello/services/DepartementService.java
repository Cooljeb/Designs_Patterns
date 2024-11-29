package fr.diginamic.hello.services;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {
    @Autowired
    DepartementRepository departementRepository;
    //DepartementDao departementDa o;

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

//    /**
//     * Méthode pour récupérer les villes depuis la base de données.
//     * via la couche d'accès aux données
//     * @return Liste des villes
//     */
////    public List<Departement> extractDepartement(){
////        return departementDao.extractDepartement();
////    }
    /**
     * Méthode pour récupérer une liste de départements avec pagination
     * @param pageable Les informations de pagination
     * @return La page des départements
     */
    public Page<Departement> extractDepartementPageable(Pageable pageable) {
        return departementRepository.findAll(pageable);
    }

    public List<Departement> extractDepartement() {
        return departementRepository.findAll();
    }



    /**
     * Méthode pour récupérer un département par son ID
     * @param id L'ID du département
     * @return Le département
     */
    public Departement extractDepartement(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun département trouvé avec l'ID : " + id));
    }
//    /**
//     * <p>Méthode pour récupérer un département à partir de son id
//     * via la couche d'accès aux données </p>
//     * <p> Si l'id est négatif, une exception est levée.</p>
//     * @param id id de la ville
//     * @return Ville
//     */
//    public Departement extractDepartement(Long id)  {
//
//        return departementDao.extractDepartement(id);
//    }

    /**
     * <p>Méthode pour récupérer un département à partir de son nom </p>
     * <p>Si le nom est vide, une exception est levée.</p>
     *
     * @param nom
     * @return le département
     */
    public Optional<Departement> extractDepartementParNom(String nom) {
        return departementRepository.findByNom(nom);
    }

    /**
     * <p>Méthode pour ajouter un département dans la base de données </p>
     * @param departement à ajouter
     * @return Liste des départements après ajout
     */
    public List<Departement> insertDepartement(Departement departement) {
        Departement nouveauDepartement = departement;
        if(nouveauDepartement.getNom() != null) {
            nouveauDepartement.setNom(departement.getNom());
        } else if (!departementRepository.existsByNom(departement.getNom())) {
            departementRepository.save(nouveauDepartement);
        }

        return departementRepository.findAll();
    }


    /**
     * <p>Méthode pour modifier un département dans la base de données </p>
     * <p>On teste si le département existe et que la valeur n'est pas nulle avant de la modifier </p>
     * @param idDepartement
     * @param departementModifie
     * @return Liste des villes après modification
     */
    public List<Departement>modifierDepartement(Long idDepartement, Departement departementModifie) {
        if(departementRepository.existsById(idDepartement)){
            if(departementModifie!= null){
                departementRepository.save(departementModifie);
            }
        }
        return departementRepository.findAll();
    }

    /**
     * <p>Méthode pour supprimer un département dans la base de données </p>
     * <p>On teste si la ville existe et que la valeur n'est pas nulle avant de la supprimer </p>
     * @param idDepartement
     * @return Liste des villes après suppression
     */
    public List<Departement> supprimerDepartement(Long idDepartement) {

        if (departementRepository.existsById(idDepartement)) {
            departementRepository.deleteById(idDepartement);
        }
        return departementRepository.findAll();
    }
}
