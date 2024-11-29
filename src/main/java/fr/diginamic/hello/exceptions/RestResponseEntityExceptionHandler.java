package fr.diginamic.hello.exceptions;

import fr.diginamic.hello.controleurs.VilleControleur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public abstract class  RestResponseEntityExceptionHandler {

    protected ResponseEntity<String>ErreurVilleAbsente(VilleControleur vce){
        return ResponseEntity.badRequest().body("Erreur lors de la récupération des données de la ville.");
    }

    protected ResponseEntity<String>ErreurInsertionVille(VilleControleur vce){
        return ResponseEntity.badRequest().body("Erreur lors de l'insertion de la ville.");
    }
    protected ResponseEntity<String> ErreurModificationVille(VilleControleur vce){
        return ResponseEntity.badRequest().body("Erreur lors de la modification de la ville.");
    }
}
