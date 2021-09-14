package duke.exception;

/**
 * An exception to handle the case for a lack of input of search word.
 */
public class FindCommandWordSuppliedException extends DukeException {

    public FindCommandWordSuppliedException() {
        super("You need to include a search word. try \"find search_input_here\"");
    }
}
