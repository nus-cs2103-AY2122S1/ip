package model.vocab.exceptions;

public class UnrecognisedPhraseException extends Exception {
    /**
     * Default constructor of UnrecognisedPhraseException.
     * To be used when a phrase is not contained in a vocab list.
     *
     * @param errorMessage message to be stored in the UnrecognisedPhraseException
     */
    public UnrecognisedPhraseException(String errorMessage) {
        super(errorMessage);
    }
}
