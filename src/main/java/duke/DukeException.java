package duke;

/**
 * An exception class that allows the program to throw one type of exception
 * when the program encounters a problem.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

}
