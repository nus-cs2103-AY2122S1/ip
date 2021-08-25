package duke.exception;

/**
 * Signals that there is a missing argument when parsing a command.
 */
public class MissingArgumentException extends DukeException {
    /**
     * Constructs a MissingArgumentException specifying which argument is missing for the given task.
     *
     * @param task     Task that is missing an argument.
     * @param argument Argument that is missing.
     */
    public MissingArgumentException(String task, String argument) {
        super(task + " is missing the " + argument + " argument.");
    }
}
