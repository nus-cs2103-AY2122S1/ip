package duke.exception;

/**
 * Thrown to indicate that the keyword is missing when the user enters
 * a command that finds tasks with the matching keyword.
 */
public class MissingKeywordException extends DukeException {

    /**
     * Constructs a MissingKeywordException with a detail message.
     */
    public MissingKeywordException() {
        super("Keyword is not specified!\nPlease follow this format: [find] [keyword]");
    }
}
