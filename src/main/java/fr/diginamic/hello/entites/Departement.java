package fr.diginamic.hello.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de département
 */
@Entity
@Table
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    /*** ID du département*/
    private Long id;

    /*** Nom du département*/
    @Column(nullable = false)
    private String nom;
    /** Code du département*/
    @Column(nullable = false)
    private String code;
    @JsonIgnore
    @OneToMany(mappedBy = "departement")
    private List<Ville> villes = new ArrayList<Ville>();

    private Integer nbHabitants;
    /**Département par défaut*/
    public Departement(){

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
     * Renvoie la valeur de {@link #code}.
     *
     * @return la valeur actuelle de code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Constructeur de département avec nom et code
     * @param nom Nom du département
     * @param code Code du département
     */
    public Departement(String nom, String code) {
        this.nom = nom;


        this.code = code;
    }

    /**
     * Renvoie la valeur de {@link #id}.
     *
     * @return la valeur actuelle de id.
     */
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Departement{");
        sb.append("nom='").append(nom).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Renvoie la valeur de {@link #villes}.
     *
     * @return la valeur actuelle de villes.
     */
    public List<Ville> getVilles() {
        return villes;
    }

    /**
     * Définit la valeur de {@link #villes}.
     *
     * @param villes la nouvelle valeur de villes.
     */
    public void setVilles(List<Ville> villes) {
        this.villes = villes;
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
     * Définit la valeur de {@link #nom}.
     *
     * @param nom la nouvelle valeur de nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit la valeur de {@link #code}.
     *
     * @param code la nouvelle valeur de code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNbHabitants(){
        int total = 0;
        for(Ville ville : villes){
            total += ville.getNbHabitants();
        }
        return total;
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
