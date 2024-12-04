package fr.diginamic.hello.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import fr.diginamic.hello.exceptions.DepartementExceptions.ErreurTailleCodeDepartementException;
import fr.diginamic.hello.exceptions.DepartementExceptions.ErreurNomDepartementException;
import fr.diginamic.hello.exceptions.DepartementExceptions.ErreurCodeDepartementException;

@ControllerAdvice
public class DepartementExceptionsHandler {

    @ExceptionHandler({ErreurTailleCodeDepartementException.class})
    protected ResponseEntity<String> handlerErreurCodeDepartement(ErreurTailleCodeDepartementException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({ErreurNomDepartementException.class})
    protected ResponseEntity<String> handlerErreurCodeDepartement(ErreurNomDepartementException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({ErreurCodeDepartementException.class})
    protected ResponseEntity<String> handlerErreurCodeDepartementExceptions(ErreurCodeDepartementException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
