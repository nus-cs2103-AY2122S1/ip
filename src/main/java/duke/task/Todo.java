package duke.task;

import duke.main.DukeException;


/**
 * Represents a todo duke.task.
 *
 * @author Gordon Yit
 * @version C2103T, Semester 2
 */
public class Todo extends Task {

    private final String TODO_MARKER = "T";
    private final String TODO_KEYWORD = "todo ";
    private String todoDescription;

    /**
     * Class constructor.
     *
     * @param description the duke.task description.
     * @throws DukeException cause by exception handled in DukeException class.
     */
    public Todo(String description) throws DukeException {
        super();
        try {
            int startOfDescriptionIndex = getStartingIndexAfter(description, TODO_KEYWORD);
            todoDescription = getSubString(description, startOfDescriptionIndex);
            if (todoDescription == "") {
                throw new StringIndexOutOfBoundsException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        }
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param todoDescription description of todo duke.task.
     * @param dateOfTask      date of the todo duke.task (unused).
     */
    public Todo(String todoDescription, String dateOfTask) {
        this.todoDescription = todoDescription;
    }
    /**
     * Prints out the duke.task.
     *
     * @return string format of the todo duke.task,
     * consisting of the duke.task marker "T" and duke.task description.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", TODO_MARKER, super.toString(), todoDescription);
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s", TODO_MARKER, getStatusIcon() == " " ? 1 : 0,
            todoDescription);
    }
}
