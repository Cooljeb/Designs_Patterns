package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementControleur {

    @Autowired
    private DepartementService departementService;

    @GetMapping
    public List<Departement> extractDepartement(){
        return departementService.extractDepartement();
    }

    @GetMapping("/{id}")
    public Departement extractDepartement(@PathVariable("id")  Long id) {
        return departementService.extractDepartement(id);
    }


    @GetMapping("/nom/{nom}")
    public Departement extractDepartement(@PathVariable("nom")  String nom) {
        return departementService.extractDepartement(nom);
    }

    @PostMapping
    public ResponseEntity<String> insertDepartement(@RequestBody Departement departement) {

        try {
            departementService.insertDepartement(departement);
            return new ResponseEntity<String>("Succès !", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>("La ville existe déjà !",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public ResponseEntity<String> modifierDepartement( @RequestBody Departement departement) {

        try {
            departementService.modifierDepartement(departement.getId(), departement);
            return new ResponseEntity<String>("Succès !",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerDepartement(@PathVariable Long id) {
        try{
            departementService.supprimerDepartement(id);
            return new ResponseEntity<>("Succès!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("La supression a échouée !",HttpStatus.BAD_REQUEST);
        }
    }

}
