package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.entites.Ville;
import fr.diginamic.hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControlleur {

    @Autowired
    VilleService villes;
    @GetMapping("/mesVilles")
    public List<Ville> afficherMesVilles(){
        return villes.getMesVilles();
    }
}
