package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe contrôleur pour la page d'accueil'
 */
@RestController //permet de dire à SpringBoot qu'il s'agit d'un controleur
@RequestMapping("/hello") // lors de l'appel de la page HTTP://localhost:8087/hello, Spring Boot appellera la méthode direHello()
public class HelloControleur { // permet de mapper la classe HelloControleur à l'URL "/hello"'


    @Autowired // permet d'injecter la dépendance HelloService dans HelloControleur
    public HelloControleur(HelloService hello) {
        this.hello = hello;
    }
    private HelloService hello;

    @GetMapping
    public String direHello() {
        return hello.salutations();
    }
}
