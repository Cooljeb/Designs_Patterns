package fr.diginamic.hello.dto;

import fr.diginamic.hello.entites.Departement;
import fr.diginamic.hello.entites.Ville;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VilleMapper {
    /**
     * Convertie une entité Ville en DTO VilleDto
     *
     * @param ville Ville à convertir
     * @return DTO VilleDto
     */
    public static VilleDto toDto(Ville ville) {
        // Création du DTO et initialisation des attributs
        VilleDto villeDto = new VilleDto();

        villeDto.setId(ville.getId());
        villeDto.setNom(ville.getNom().toUpperCase());
        villeDto.setNbHabitants(ville.getNbHabitants());
        if (ville.getDepartement() != null) {
            villeDto.setCodeDepartement(ville.getDepartement().getCode());
            villeDto.setNomdepartement(ville.getDepartement().getNom().toUpperCase());
        }
        return villeDto;
    }

    public static List<VilleDto> toDtoList(List<Ville> villes) {
        // Création de l'entité Ville et initialisation des attributs
        return villes.stream()
                .map(VilleMapper::toDto)
                .collect(Collectors.toList());

    }

    public static Ville toBean(VilleDto villeDto) {
        Ville bean = new Ville();
        Departement departement = new Departement();
        bean.setId(villeDto.getId());
        bean.setNom(villeDto.getNom().toLowerCase());
        bean.setNbHabitants(villeDto.getNbHabitants());
        departement.setCode(villeDto.getCodeDepartement());
        departement.setNom(villeDto.getNomdepartement().toLowerCase());
        return  bean;
    }
}

