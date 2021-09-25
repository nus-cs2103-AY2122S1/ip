package model.vocab.exceptions;

public class DuplicateVocabException extends Exception {
    /**
     * Default constructor of EmptyDescriptionException.
     * To be used when vocab already exists or default to Alice.
     *
     * @param errorMessage message to be stored in the EmptyDescriptionException
     */
    public DuplicateVocabException(String errorMessage) {
        super(errorMessage);
    }
}
