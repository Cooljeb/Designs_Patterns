package fr.diginamic.hello.services;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    /**déclaration du DAO pour la gestion des villes*/
    @Autowired
    //private VilleDao villeDao;
    private VilleRepository villeRepository;

    /**
     * Initialisation des villes de départ pour la base de données
     * utilsation de l'anotation "@PostConstruct"
     */
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
    public List<Ville> extractVille(){
        return villeRepository.findBy();
        //return villeDao.extractVille();
    }
    public List<Ville> extractVille(int page, int taille) {
        int offset = page * taille; // Calcul de l'offset
        return villeRepository.findAll(offset, taille);
    }

    /**
     * <p>Méthode pour récupérer une ville à partir de son id
     * via la couche d'accès aux données </p>
     * <p> Si l'id est négatif, une exception est levée.</p>
     * @param id id de la ville
     * @return Ville
     */
    public Ville extractVille(Long id)  {
            return villeRepository.findById(id).get();
        //return villeDao.extractVille(id);
    }

    /**
     * <p>Méthode pour récupérer une ville à partir de son nom </p>
     * <p>Si le nom est vide, une exception est levée.</p>
     * @param nom
     * @return Ville
     */
    public Ville extractVille(String nom) {
            //return villeDao.extractVille(nom);
        return villeRepository.findByNom(nom);
    }

    /**
     * <p>Méthode pour ajouter une ville dans la base de données </p>
     * @param ville à ajouter
     * @return Liste des villes après ajout
     */
    public List<Ville> insertVille(Ville ville) {
        Ville NouvelleVille = ville;
        if(ville.getDepartement() != null) {
            NouvelleVille.setDepartement(ville.getDepartement());
        }
        if(!villeRepository.existsByNom(ville.getNom())){
            villeRepository.save(NouvelleVille);
        }
        return villeRepository.findBy();
        //return villeDao.insertVille(ville);
    }
//        villeDao.insertVille(ville);
//        return villeDao.extractVille();



    /**
     * <p>Méthode pour modifier une ville dans la base de données </p>
     * <p>On teste si la ville existe et que la valeur n'est pas nulle avant de la modifier </p>
     * @param idVille
     * @param villeModifiee
     * @return Liste des villes après modification
     */
    public List<Ville>modifierVille(Long idVille, Ville villeModifiee) {
        //Ville villeAModifier = villeDao.extractVille(idVille);
        if(villeRepository.existsById(idVille)){
            if(villeModifiee!= null){
                villeRepository.save(villeModifiee);
            }
        }
        return villeRepository.findBy();
    }

    /**
     * <p>Méthode pour supprimer une ville dans la base de données </p>
     * <p>On teste si la ville existe et que la valeur n'est pas nulle avant de la supprimer </p>
     * @param idVille
     * @return Liste des villes après suppression
     */
    public List<Ville> supprimerVille(Long idVille) {
        //Ville villeASupprimer = villeDao.extractVille(idVille);
        if(villeRepository.existsById(idVille)){
            villeRepository.deleteById(idVille);
        }
        return villeRepository.findBy();
    }
//        Ville villeASupprimer = villeDao.extractVille(idVille);
//        if (villeASupprimer!= null) {
//            villeDao.supprimerVille(villeASupprimer.getId());
//        }
//        return villeDao.extractVille();


    public List<Ville> rechercheVilleLesPlusPeuplees(String codeDep, Integer n) {
//       return villeDao.rechercheVilleLesPlusPeuplees(codeDep,n);
        Pageable pageable = PageRequest.of(0, n);
        return villeRepository.findByDepartementOrderByNbHabitants(codeDep, pageable);
    }

    public List<Ville> findAllByNomStartingWith(String nom){
        return villeRepository.findAllByNomStartingWith(nom);
    }

    public List<Ville> findAllByNbHabitantsGreaterThan(Integer min){
        return villeRepository.findAllByNbHabitantsGreaterThan(min);
    }

    public List<Ville> findAllByNbHabitantsBetween(Integer min, Integer max){
        return villeRepository.findAllByNbHabitantsBetween(min, max);
    }

    public List<Ville> findByDepartement_idAndNbHabitantsGreaterThan(Long departement_id, Integer nbHabitants){
        return villeRepository.findByDepartement_idAndNbHabitantsGreaterThan(departement_id, nbHabitants);
    }

    public List<Ville> findByDepartement_idAndNbHabitantsBetween(Long departement_id, Integer min, Integer max){
        return villeRepository.findByDepartement_idAndNbHabitantsBetween(departement_id, min, max);
    }

    public List<Ville>findByDepartement_idOrderByNbHabitantsDesc(Long departement_id,Integer n){
        Pageable pageable = PageRequest.of(0, n);
        return villeRepository.findByDepartement_idOrderByNbHabitantsDesc(departement_id, pageable);
    }




}
