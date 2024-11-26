package fr.diginamic.hello.entites;

import jakarta.persistence.*;

/**
 * Classe des villes
 */
@Entity
@Table(name = "Ville")
public class Ville {

    /**id de la ville*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    /**nom de la ville*/
    @Column(name = "NOM", nullable = false, length =100)
    private String nom;
    /**nombre d'habitants de la ville*/
    @Column(name = "NB_HABITANTS", nullable = false, length =10)
    private Integer nbHabitants;

    /**
     * Constructeur par défaut de la ville pour JPA
     */
    public Ville() {

    }

    /**
     * Constructeur de la ville
     * @param nom nom de la ville
     * @param nbHabitants nombre d'habitants de la ville
     */
    public Ville(String nom, Integer nbHabitants) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    /**
     * Constructeur de la ville avec un ID
     * @param id
     * @param nom
     * @param nbHabitants
     */
    public Ville(Integer id, String nom, Integer nbHabitants) {
        this.id = id;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
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
     * Renvoie la valeur de {@link #id}.
     *
     * @return la valeur actuelle de id.
     */
    public Integer getId() {
        return id;
    }
}
