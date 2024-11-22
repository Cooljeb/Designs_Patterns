package fr.diginamic.hello.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class HelloService {

    @GetMapping
    public String salutations() {
        return "Je suis la classe service et je vous dis Bonjour 😎!";
    }
}
