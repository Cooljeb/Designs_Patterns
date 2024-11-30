package fr.diginamic.hello.exceptions;

/**
 * Classe de lien avec {VillesExceptionsHandler}
 *
 */
public class VillesExceptions extends RuntimeException{

    /**
     * Constructeur de l'exception qui sera liée par le code au message spécifique
     * @param message
     */
    public VillesExceptions(String message) {
        super((message));
    }
/*--------------------------------------------------------------------------*/
/// Création des sous-classes directemen ici
    /**
     * Exception du cas où une ville est absente
     */
    public class ErreurVilleAbsenteExceptions extends VillesExceptions {
        public ErreurVilleAbsenteExceptions(String nom) {
            super("Aucune ville dont le nom commence par  " + nom + " n’a été trouvée");
        }
    }
    /*##########################################*/
    /*EXCEPTIONS LORS DES INSERTIONS DE VILLES*/
    /*##########################################*/
    /**
     * <p>Exception concernant le nombre d'habitants minimum pour une ville
     * lors de l'insertion</p>
     */
    public class ErreurNombreHabitantMinimunVilleExceptions extends VillesExceptions {
        public ErreurNombreHabitantMinimunVilleExceptions(Integer nbHabitant) {
            super("La ville doit avoir au moins 10 habitants !! Nombre Habitant "+nbHabitant+" insuffisant");
        }
    }

    /**
     * <p>Exception concernant le nombre minimum de caractères dans le nom de la ville</p>
     */
    public class ErreurNomVilleExceptions extends VillesExceptions {
        public ErreurNomVilleExceptions(String nom) {
            super("La ville doit avoir un nom contenant au moins 2 lettres!! Nom de ville "+nom+" trop court");
        }
    }

    /**
     * <p>Exception concernant le nombre de caractères du code département</p>
     */
    public class ErreurNbCaractereDepartementExceptions extends VillesExceptions {
        public ErreurNbCaractereDepartementExceptions(String codeDepartement) {
            super("Le code département doit obligatoirement avoir " +
                    "2 caractères!! Code département "+codeDepartement+" trop court");
        }
    }

    /**
     * <p>Exception concernant l'unicité du nom d'une ville pour un département donné</p>
     */
    public class ErreurNomVilleDepartementExceptions extends VillesExceptions {
        public ErreurNomVilleDepartementExceptions(String nom) {
            super("Le nom de la ville " + nom + " est déjà utilisé pour le département.");
        }
    }

    /**
     * <p>Exception traitant la recherche de villes dont le nombre d'habitants est supérieur au minimum
     */
    public class ErreurRechercheVilleNombreHabitantsSupAMinExceptions extends VillesExceptions {
        public ErreurRechercheVilleNombreHabitantsSupAMinExceptions(Integer min) {
            super("Aucune ville n’a une population supérieure à " + min);
        }
    }

    /**
     * <p>Exception traitant la recherche de villes dont le nombre d'habitants est supérieur au minimum
     * et inférieur à max</p>
     */
    public class ErreurRechercheVilleNombreHabitantsMinMaxExceptions extends VillesExceptions {
        public ErreurRechercheVilleNombreHabitantsMinMaxExceptions(Integer min, Integer max) {
            super("Exception traitant la recherche de villes dont le" +
                    " nombre d'habitants est supérieur au minimum" + min + " et " + max);
        }
    }



}
