package duke.errors;

/**
 * Special Exception class for Duke.java.
 */
public class DukeException extends Exception {
    private String errorMessage;

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        System.out.println(errorMessage);
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
