package duke.task;

import duke.main.DukeException;

/**
 * Represents a todo duke.task.
 *
 * @author Gordon Yit
 * @version C2103T, Semester 2
 */
public class Todo extends Task {

    public static final String TODO_MARKER = "T";
    private static final String TODO_KEYWORD = "todo ";
    private String todoDescription;
    private TaskTag todoTag;

    /**
     * Class constructor.
     *
     * @param description the todo description.
     * @throws DukeException cause by exception handled in DukeException class.
     */
    public Todo(String description) throws DukeException {
        super();
        try {
            int startOfDescriptionIndex = getStartingIndexAfter(description, TODO_KEYWORD);
            int startOfTodoTagIndex = getStartingIndexAfter(description,
                TaskTag.getTagSymbol()) - TaskTag.getTagSymbol().length();
            assert startOfDescriptionIndex != -1 : "todo must be in the description.";
            this.todoDescription = getSubString(description, startOfDescriptionIndex,
                    startOfTodoTagIndex);
            todoTag = new TaskTag(getSubString(description, startOfTodoTagIndex));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        }
    }

    /**
     * Class constructor for loading task from storage.
     *
     * @param todoDescription description of the todo task.
     */
    public Todo(String ...todoDescription) {
        this.todoDescription = todoDescription[0];
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
        return String.format("%s %s %s %s", TODO_MARKER, super.formatToStore(),
            todoDescription, todoTag.getTagInStoreFormat());
    }
}
