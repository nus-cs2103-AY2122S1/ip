package genie.exception;

/**
 * A class that represents the exception that is thrown
 * when the format of the description of the Task is invalid.
 */
public class GenieException extends Exception{

    /**
     * A Constructor for the custom exception
     * @param error String of the error outputted
     */
    public GenieException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "You need to use the magic words Aladdin.\n" +
                "To find out what they are, say 'help'";
    }
}