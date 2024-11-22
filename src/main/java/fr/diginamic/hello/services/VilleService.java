package fr.diginamic.hello.services;

import fr.diginamic.hello.entites.Ville;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VilleService {

    private List<Ville> mesVilles = new ArrayList<>(Arrays.asList(
            new Ville("Paris", 22_000_000),
            new Ville("London", 8_980_000),
            new Ville("Berlin", 3_670_000),
            new Ville("Madrid", 3_260_000)
    ));
    @GetMapping
    public List<Ville> getMesVilles() {
        return mesVilles;
    }
}
