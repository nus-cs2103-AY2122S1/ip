/**
 * class for exceptions relating to todo, deadline, event commands,
 * ie. exceptions that occur during creation of tasks.
 *
 */
public class DukeTaskDetailsException extends DukeException {

    DukeTaskDetailsException(String message) {
        super(message);
    }
}
