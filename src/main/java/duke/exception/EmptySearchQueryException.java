package duke.exception;

public class EmptySearchQueryException extends DukeException {

    private static final String EMPTY_SEARCH_QUERY_MSG = "Your search query cannot be empty!";

    public EmptySearchQueryException() {
        super(EMPTY_SEARCH_QUERY_MSG);
    }
}
