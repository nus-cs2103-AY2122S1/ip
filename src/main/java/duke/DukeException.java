package duke;


public class DukeException extends RuntimeException{

    /**
     * A Constructor for the custom exception
     * @param error The error outputted
     */
    public DukeException(String error) {
        super(error);
    }
}