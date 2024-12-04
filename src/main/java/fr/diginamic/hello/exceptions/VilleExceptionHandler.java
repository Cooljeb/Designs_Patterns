package fr.diginamic.hello.exceptions;

import fr.diginamic.hello.controleurs.VilleControleur;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurNombreHabitantMinimunVilleExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurRechercheVilleNombreHabitantsMinMaxExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurRechercheVilleNombreHabitantsSupAMinExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurVilleAbsenteExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurNomVilleExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurNbCaractereDepartementExceptions;
import fr.diginamic.hello.exceptions.VillesExceptions.ErreurNomVilleDepartementExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Gestion des exceptions pour la ville avec des messages personalisés

 */
@ControllerAdvice
public  class  VilleExceptionHandler {
    /**
     * @param
     * @param ex exception levée
     * @return Réponse HTTP avec le code erreur et le message personalisé
     */
    @ExceptionHandler({ErreurVilleAbsenteExceptions.class})
    protected ResponseEntity<String>handleErreurVilleAbsente(ErreurVilleAbsenteExceptions ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({ErreurRechercheVilleNombreHabitantsSupAMinExceptions.class})
    protected ResponseEntity<String> handleErreurRechercheVilleNombreHabitantsSupAMin
            (ErreurRechercheVilleNombreHabitantsSupAMinExceptions ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({ErreurRechercheVilleNombreHabitantsMinMaxExceptions.class})
    protected ResponseEntity<String> handleErreurRechercheVilleNombreHabitantsMinMax
            (ErreurRechercheVilleNombreHabitantsSupAMinExceptions ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /*##########################################*/
    /*EXCEPTIONS LORS DES INSERTIONS DE VILLES*/
    /*##########################################*/
    @ExceptionHandler({ErreurNombreHabitantMinimunVilleExceptions.class})
    protected ResponseEntity<String> handleErreurNombreHabitantMinimunVille
    (ErreurNombreHabitantMinimunVilleExceptions ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler({ErreurNomVilleExceptions.class})
    protected ResponseEntity<String> handleErreurNomVille(ErreurNomVilleExceptions ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler({ErreurNbCaractereDepartementExceptions.class})
    protected ResponseEntity<String> handleErreurNbCaractereDepartement(ErreurNbCaractereDepartementExceptions ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler({ErreurNomVilleDepartementExceptions.class})
    protected ResponseEntity<String> handleErreurNomVilleDepartement(ErreurNomVilleDepartementExceptions ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
