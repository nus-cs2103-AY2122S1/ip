package duke.exception;

public class FindCommandWordSuppliedException extends DukeException {

    public FindCommandWordSuppliedException() {
        super("You need to include a search word. try \"find search_input_here\"");
    }
}
