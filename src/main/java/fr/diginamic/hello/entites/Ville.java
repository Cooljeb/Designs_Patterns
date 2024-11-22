package fr.diginamic.hello.entites;

/**
 * Classe des villes
 */
public class Ville {
    /**nom de la ville*/
    private String nom;
    /**nombre d'habitants de la ville*/
    private Integer nbHabitants;

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
