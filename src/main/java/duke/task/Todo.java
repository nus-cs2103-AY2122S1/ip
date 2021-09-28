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


    /**
     * Class constructor for loading task from storage.
     *
     * @param todoDescription description of the todo task.
     */
    public Todo(String todoDescription) {
        super();
        this.todoDescription = todoDescription;
    }
    /**
     * Class constructor.
     *
     * @param description the todo description.
     * @throws DukeException cause by exception handled in DukeException class.
     */
    public static Todo of(String description) throws DukeException {
        int startOfTodoTagIndex = -1;
        String tag = "";
        int startOfDescriptionIndex = getStartingIndexAfter(description, TODO_KEYWORD);
        assert startOfDescriptionIndex != -1 : "todo must be in the description.";
        String taskDescription = getSubString(description, startOfDescriptionIndex);
        try {
            if (description.contains(TaskTag.getTagSymbol())) {
                startOfTodoTagIndex = getStartingIndexAfter(description,
                    TaskTag.getTagSymbol()) - TaskTag.getTagSymbol().length();
                tag = getSubString(description, startOfTodoTagIndex);
                taskDescription = getSubString(description, startOfDescriptionIndex, startOfTodoTagIndex);
            }
            Todo todo = new Todo(taskDescription);
            todo.addTag(tag);
            return todo;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        }
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
                getTag());
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s %s %s %s", TODO_MARKER, super.formatToStore(),
            todoDescription, getTagFormattedForStorage());
    }
}
