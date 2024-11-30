package fr.diginamic.hello.exceptions;

public class DepartementExceptions extends RuntimeException{

    public DepartementExceptions(String message) {
        super(message);
    }

    /**
     * <p>Gestion de l'exception "Le code département fait au maximum 3 caractères et au minimum 2"</p>
     */
    public class ErreurTailleCodeDepartementException extends DepartementExceptions{
        public ErreurTailleCodeDepartementException(String codeDpt) {
            super(codeDpt+" incorrect, taille min 2, max 3");
        }
    }

    /**
     * <p>Gestion de l'exception "Le nom du département est obligatoire et comporte au moins 3 lettres"</p>
     */
    public class ErreurNomDepartementException extends DepartementExceptions{
        public ErreurNomDepartementException(String nomDpt) {
            super(nomDpt+" obligatoire et comporte au moins 3 lettres");
        }
    }

    /**
     * <p>Exception concernant l'unicité du code d'un département </p>
     */
    public class ErreurCodeDepartementException extends DepartementExceptions {
        public ErreurCodeDepartementException(String nom) {
            super("Ce code de département" + nom + " est déjà utilisé pour le département.");
        }
    }
}
