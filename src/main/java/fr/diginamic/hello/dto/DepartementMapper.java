package fr.diginamic.hello.dto;

import fr.diginamic.hello.entites.Departement;

import java.util.List;
import java.util.stream.Collectors;

public class DepartementMapper {

    /**
     * Convertie une entité Departement en DTO DepartementDto
     *
     * @param departement Departement à convertir
     * @return DTO DepartementDto
     */
    public static DepartementDto toDto(Departement departement) {
        // Création du DTO et initialisation des attributs
        DepartementDto departementDto = new DepartementDto();

        departementDto.setCodeDepartement(departement.getCode());
        departementDto.setNom(departement.getNom().toUpperCase());
        departementDto.setNbHabitants(departement.getNbHabitants());

        return departementDto;
    }

    /**
     * Convertie une liste d'entités Departement en une liste de DTO DepartementDto
     *
     * @param departements Liste d'entités Departement
     * @return Liste de DTO DepartementDto
     */
    public static List<DepartementDto> toDtoList(List<Departement> departements) {
        // Conversion de la liste d'entités Departement en une liste de DTO DepartementDto
        return departements.stream()
                .map(DepartementMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convertie un DTO DepartementDto en une entité Departement
     *
     * @param departementDto DTO DepartementDto à convertir
     * @return Entité Departement
     */
    public static Departement toBean(DepartementDto departementDto) {
        // Création de l'entité Departement et initialisation des attributs
        Departement departement = new Departement();

        departement.setCode(departementDto.getCodeDepartement());
        departement.setNom(departementDto.getNom().toLowerCase());
        departement.setNbHabitants(departementDto.getNbHabitants());

        return departement;
    }
}
