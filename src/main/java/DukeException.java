package main.java;

/**
 * DukeException encapsulates all the exceptions that could occur from Duke.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param errorMessage indicates the error that has occurred
     */
    DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
