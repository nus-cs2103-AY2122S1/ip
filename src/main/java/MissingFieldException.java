/**
 * This Exception is thrown when either:
 * "deadline" is called without passing in a due date with "/by"
 * or "event" is called without passing in a timing with "/at"
 *
 */

public class MissingFieldException extends DukeException {
}
