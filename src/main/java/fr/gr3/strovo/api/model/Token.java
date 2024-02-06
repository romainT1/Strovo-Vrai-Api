package fr.gr3.strovo.api.model;

/**
 * Décrit un token.
 */
public class Token {

    /** Valeur du token. */
    private String value;

    /**
     * Crée une instance de token.
     * @param tokenValue valeur du token
     */
    public Token(final String tokenValue) {
        this.value = tokenValue;
    }

    /**
     * @return la valeur du token
     */
    public String getValue() {
        return value;
    }

    /**
     * Modifie la valeur du token.
     * @param newValue nouvelle valeur du token
     */
    public void setValue(final String newValue) {
        this.value = newValue;
    }
}
