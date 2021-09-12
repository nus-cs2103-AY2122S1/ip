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
    private TaskTag todoTag;

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
	    assert startOfDescriptionIndex != -1 : "TASK KEYWORD must be in the description.";
            todoDescription = getSubString(description, startOfDescriptionIndex);
            if (todoDescription.equals("")) {
                throw new StringIndexOutOfBoundsException();
            }
            int startOfTag = getStartingIndexAfter(description, TaskTag.getTagSymbol());
            String tag = getSubString(description, startOfTag - TaskTag.getTagSymbol().length());
            todoTag = new TaskTag(tag);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        }
    }

    /**
     * Class constructor used when reading storage file.
     *
     * @param todoDescription description of the todo file.
     * @param tag tag used to tag the task.
     */
    public Todo(String todoDescription, String tag) {
        this.todoDescription = todoDescription;
        this.todoTag = new TaskTag(tag);
    }
    /**
     * Prints out the duke.task.
     *
     * @return string format of the todo duke.task,
     * consisting of the duke.task marker "T" and duke.task description.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s %s", TODO_MARKER, super.toString(), todoDescription,
                todoTag.getTag());
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s", TODO_MARKER, getStatusIcon().equals(" ") ? 1 : 0,
            todoDescription, todoTag.getTagInStoreFormat());
    }
}
