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
    VilleService villes;


    @GetMapping
    public List<Ville> extractVille(){
        return villes.extractVille();
    }
    @GetMapping("/{id}")
    public List<Ville> extractVille(int id) {
        return villes.extractVille();
    }

    @PostMapping
    public ResponseEntity<String> insertVille(@Valid @RequestBody Ville ville, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<String>("Problème de validation des contraintes !",HttpStatus.BAD_REQUEST);
        }
        if (villes.villeVerif(ville)) {
            villes.insertVille(ville);
            return new ResponseEntity<String>("Succès !",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("La ville existe déjà !",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> modifierVille(@Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Problème de validation des contraintes !",HttpStatus.BAD_REQUEST);
        }
        if (villes.villeVerif(ville)) {
            villes.modifierVille(ville.getId(), ville);
            return new ResponseEntity<String>("Succès !",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerVille(@PathVariable Long id) {
        int v = Integer.parseInt(id.toString()); // conversione int de l'id
        try{
            villes.supprimerVille(v);
            return new ResponseEntity<>("Succès!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
        }
    }

}
