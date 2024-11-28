package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.services.VilleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villes;


    /**
     * Méthode extractVille qui renvoi la liste de villes connues dans la base
     * @return  la liste de toutes les villes
     */
    @GetMapping
    public List<Ville> extractVille(){
        return villes.extractVille();
    }
    /**
     * Méthode extractVille
     * @param  id corresponf à l'id de la ville recherchée
     * @return la ville correspondant à l'id passé en paramètre
     */
    @GetMapping("/{id}")
    public Ville extractVille(@PathVariable("id")  Long id) {
        return villes.extractVille(id);
    }

    /**
     * Méthode extractVille
     * @param  nom corresponf à l'id de la ville recherchée
     * @return la ville correspondant à l'id passé en paramètre
     */
    @GetMapping("/nom/{nom}")
    public Ville extractVille(@PathVariable("nom")  String nom) {
        return villes.extractVille(nom);
    }

    @PostMapping
    public ResponseEntity<String> insertVille(@RequestBody Ville ville) {

        try {
            villes.insertVille(ville);
            return new ResponseEntity<String>("Succès !", HttpStatus.OK);
        }catch (Exception e) {
                return new ResponseEntity<String>("La ville existe déjà !",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> modifierVille( @RequestBody Ville ville) {

        try {
            villes.modifierVille(ville.getId(), ville);
            return new ResponseEntity<String>("Succès !",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerVille(@PathVariable Long id) {
        try{
            villes.supprimerVille(id);
            return new ResponseEntity<>("Succès!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Les n plus grandes villes d'un dep
     */
    @GetMapping("/rechercheVilleLesPlusPeuplees/{codeDep}/{n}")
    public List<Ville> rechercheVilleLesPlusPeuplees(@PathVariable("codeDep")String codeDep, @PathVariable("n") Integer n) {
        return villes.rechercheVilleLesPlusPeuplees(codeDep,n);
    }

    /**
     * Recherche de toutes les villes dont la population est supérieure à min
     */
    @GetMapping("/rechercheVillePopulationSuperieureAMin/Min/{min}")
    public List<Ville> rechercheVillePopulationSuperieureAMin(@PathVariable("min")Integer min) {
        return villes.findAllByNbHabitantsGreaterThan(min);
    }

    /**
     * Recherche de toutes les villes dont la population est supérieure à min et inférieure à max
     */
    @GetMapping("/rechercheVillePopulationSuperieureAMinInferieureAMax/Min/{min}/Max/{max}")
    public List<Ville> rechercheVillePopulationSuperieureAMinInferieureAMax(@PathVariable("min")Integer min,@PathVariable("max")Integer max ) {
        return villes.findAllByNbHabitantsBetween(min,max);
    }

    /**
     * Recherche de toutes les villes d’un département dont la population est supérieure à min et inférieure à max
     */
    @GetMapping("/rechercheVilleDUnDepartementPopulationSuperieureAMinInferieureAMax/Departement/" +
            "{departement}/Min/{min}/Max/{max}")
    public List<Ville> rechercheVillePopulationSuperieureAMinInferieureAMax(@PathVariable("departement")Long dptId
            ,@PathVariable("min")Integer min,@PathVariable("max")Integer max ) {
        return villes.findByDepartement_idAndNbHabitantsBetween(dptId,min,max);
    }






}
