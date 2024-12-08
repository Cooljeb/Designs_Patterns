package fr.diginamic.hello.exceptions;

public class DocumentException extends RuntimeException {


        /**
         * Constructeur de l'exception qui sera liée par le code au message spécifique
         * @param message
         */
        public DocumentException(String message) {
            super((message));
        }
}
