package fr.diginamic.hello.services;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.dto.VilleMapper;
import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.exceptions.DepartementExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurNomVilleDepartementExceptions;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {

    /**déclaration du DAO pour la gestion des villes*/
    @Autowired
    //private VilleDao villeDao;
    private VilleRepository villeRepository;
    @Autowired
    private DepartementService dpService;
    @Autowired
    private DepartementRepository departementRepository;

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
    public Page<VilleDto> extractVillePageable(Pageable pageable){
        return villeRepository.findAll(pageable).map(VilleDto::new);
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

    public List <Ville> extractVille()  {
        return villeRepository.findBy();
        //return villeDao.extractVille(id);
    }

    /**
     * <p>Méthode pour récupérer une ville à partir de son nom </p>
     * <p>Si le nom est vide, une exception est levée.</p>
     * @param nom
     * @return Ville
     */
    public Ville extractVille(String nom) {

        return villeRepository.findByNom(nom)
                .orElseThrow(() -> new VillesExceptions.ErreurVilleAbsenteExceptions(nom));
    }

    /**
     * <p>Méthode pour ajouter une ville dans la base de données </p>
     * @param villeDto à ajouter
     * @return Liste des villes après ajout
     */
    public Ville insertVille(VilleDto villeDto) {
        Optional<Ville> nouvelleVille = villeRepository.findById(villeDto.getId());
        // On vérifie que la ville n'est pas déjà présente
        if(nouvelleVille.isPresent()){
            throw  new VillesExceptions("Une ville avec le nom '" + villeDto.getNom() + "' existe déjà.");
        }
        //on vérifie que le département n'existe pas encore
        Optional<Departement>dptNouvelleVille = departementRepository.findByNom(villeDto.getCodeDepartement());

        if(dptNouvelleVille.isEmpty()){
            throw  new DepartementExceptions("Le département avec le code '"
                    + villeDto.getCodeDepartement() + "' n'existe pas.");
        }

        if(dptNouvelleVille.isPresent() && nouvelleVille.isPresent()){
            throw new VillesExceptions.ErreurNomVilleDepartementExceptions(villeDto.getNom());
        }
        if(villeDto.getCodeDepartement().length()<2){
            throw new VillesExceptions.ErreurNbCaractereDepartementExceptions(villeDto.getCodeDepartement());
        }
        if(villeDto.getNbHabitants()<10){
            throw new VillesExceptions.ErreurNombreHabitantMinimunVilleExceptions(villeDto.getNbHabitants());
        }
        if(villeDto.getNom().length()<2){
            throw new VillesExceptions.ErreurNomVilleExceptions(villeDto.getNom());
        }
        Ville ville = VilleMapper.toBean(villeDto);
        ville.setDepartement(dptNouvelleVille.get());

        return villeRepository.save(ville);
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
