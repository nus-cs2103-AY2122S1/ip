package duke.exception;

/**
 * Throws exception when Task index input is not an integer.
 */
public class TaskIndexNotInteger extends DukeException {

    public TaskIndexNotInteger(int length) {
        super("Meow? That's not an integer... Please enter a task number between 1 and " + length);
    }

}
