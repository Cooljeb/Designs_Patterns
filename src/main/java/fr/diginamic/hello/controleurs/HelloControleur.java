package fr.diginamic.hello.controleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe contrôleur pour la page d'accueil'
 */
@RestController //permet de dire à SpringBoot qu'il s'agit d'un controleur
@RequestMapping("/hello") // lors de l'appel de la page HTTP://localhost:8087/hello, Spring Boot appellera la méthode direHello()
public class HelloControleur { // permet de mapper la classe HelloControleur à l'URL "/hello"'

    @GetMapping
    public String direHello() {
        return "Hello, Joe!";
    }
}
