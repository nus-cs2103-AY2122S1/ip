package model.vocab;

public class DuplicateVocabException extends Exception{
    /**
     * Default constructor of EmptyDescriptionException
     *
     * @param errorMessage message to be stored in the EmptyDescriptionException
     */
    public DuplicateVocabException(String errorMessage) {
        super(errorMessage);
    }
}
