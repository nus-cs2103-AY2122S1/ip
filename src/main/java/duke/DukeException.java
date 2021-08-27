package main.java.duke;

/**
 * DukeException encapsulates all the exceptions that could occur from Duke.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param errorMessage indicates the error that has occurred
     */
    public DukeException(String errorMessage) {
        super("\t ☹ OOPS!!! " + errorMessage);
    }
}
