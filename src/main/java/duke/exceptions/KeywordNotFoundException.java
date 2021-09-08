package duke.exceptions;

/**
 * Class that handles keyword not found exceptions.
 */
public class KeywordNotFoundException extends DukeException {
    /**
     * Occurs when Duke tries to find tasks with keyword, but can't find any.
     * @param keyword Keyword input used to find tasks.
     */
    public KeywordNotFoundException(String keyword) {
        super("Sowwy! I can'tw findw anyw taskws containing " + keyword + " ! >_<");
    }
}
