package fr.diginamic.hello.dto;

import fr.diginamic.hello.entites.Departement;

public class DepartementDto {

    private String codeDepartement;
    private String nom;
    private Integer nbHabitants;

    public DepartementDto() {
    }

    public DepartementDto(Departement departement) {
        this.codeDepartement = departement.getCode();
        this.nom = departement.getNom();
        this.nbHabitants = departement.getNbHabitants();
    }

    /**
     * Renvoie la valeur de {@link #codeDepartement}.
     *
     * @return la valeur actuelle de codeDepartement.
     */
    public String getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Définit la valeur de {@link #codeDepartement}.
     *
     * @param codeDepartement la nouvelle valeur de codeDepartement.
     */
    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    /**
     * Renvoie la valeur de {@link #nom}.
     *
     * @return la valeur actuelle de nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit la valeur de {@link #nom}.
     *
     * @param nom la nouvelle valeur de nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoie la valeur de {@link #nbHabitants}.
     *
     * @return la valeur actuelle de nbHabitants.
     */
    public Integer getNbHabitants() {

        return nbHabitants;
    }

    /**
     * Définit la valeur de {@link #nbHabitants}.
     *
     * @param nbHabitants la nouvelle valeur de nbHabitants.
     */
    public void setNbHabitants(Integer nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}