package genie.exception;

public class GenieException extends RuntimeException{

    /**
     * A Constructor for the custom exception
     * @param error The error outputted
     */
    public GenieException(String error) {
        super(error);
    }
}