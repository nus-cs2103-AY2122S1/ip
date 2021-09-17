package duke.task;

import duke.DukeException;

/**
 * Class that represents todo task.
 */
public class ToDo extends Task {
    public static final String syntax = "todo {description of todo}";
    public static final String inputExample = "todo get a life";

    private ToDo(String description) {
        super(description);
    }

    /**
     * Makes a todo task.
     *
     * @param description the description of todo task.
     * @return a todo task.
     * @throws DukeException if input is invalid.
     */
    public static ToDo of(String description) throws DukeException {
        return new ToDo(description);
    }

    /**
     * Returns string representation of todo object.
     *
     * @return string representation of todo object.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Returns string representation of todo object to be saved in hard disk.
     *
     * @return string representation of todo object to saved in hard disk.
     */
    @Override
    public String typeString() {
        return "todo" + Task.SEP + super.toSaveInFile("");
    }
}
