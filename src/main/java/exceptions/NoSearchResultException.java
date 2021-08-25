package exceptions;

/**
 * Exception raised when the user attempts to search for a task but there are no Tasks that match the search term.
 */
public class NoSearchResultException extends DukeException {
    public NoSearchResultException(String errorMessage) {
        super(errorMessage);
    }
}
