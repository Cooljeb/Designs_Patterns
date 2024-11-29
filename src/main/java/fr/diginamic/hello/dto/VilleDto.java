package fr.diginamic.hello.dto;

import fr.diginamic.hello.entites.Departement;

/**
 * Ville DTO qui décrit qu'on renvoi au client
 */
public class VilleDto {

    private Long id;
    private String nom;
    private Integer nbHabitants;
    private String codeDepartement;
    private String nomdepartement;

    public VilleDto() {

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

    /**
     * Renvoie la valeur de {@link #nomdepartement}.
     *
     * @return la valeur actuelle de nomdepartement.
     */
    public String getNomdepartement() {
        return nomdepartement;
    }

    /**
     * Définit la valeur de {@link #nomdepartement}.
     *
     * @param nomdepartement la nouvelle valeur de nomdepartement.
     */
    public void setNomdepartement(String nomdepartement) {
        this.nomdepartement = nomdepartement;
    }

    /**
     * Renvoie la valeur de {@link #id}.
     *
     * @return la valeur actuelle de id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de {@link #id}.
     *
     * @param id la nouvelle valeur de id.
     */
    public void setId(Long id) {
        this.id = id;
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
}
