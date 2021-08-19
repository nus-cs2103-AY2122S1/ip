/**
 * class to represent exceptions relating to done and delete
 * commands, ie. commands which use index to refer to tasks
 *
 */
public class DukeIndexInputException extends DukeException {

    DukeIndexInputException(String message) {
        super(message);
    }
}
