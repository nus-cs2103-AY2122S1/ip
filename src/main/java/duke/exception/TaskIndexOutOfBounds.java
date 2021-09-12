package duke.exception;

/**
 * Throws exception when task is integer but is out of bounds of task list.
 */
public class TaskIndexOutOfBounds extends DukeException {

    public TaskIndexOutOfBounds(int i, int length) {
        super("Meow? I can't find task " + (i + 1) + "... Please enter a task number between 1 and " + length);
    }

}
