package genie.exception;

public class GenieException extends Exception{

    /**
     * A Constructor for the custom exception
     * @param error The error outputted
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