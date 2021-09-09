package duke.task;

import duke.main.DukeException;


/**
 * Represents a todo duke.task.
 *
 * @author Gordon Yit
 * @version C2103T, Semester 2
 */
public class Todo extends Task {

    private final String TASK_MARKER = "T";
    private final String TASK_KEYWORD = "todo ";
    private String taskDescription;

    /**
     * Class constructor.
     *
     * @param description the duke.task description.
     * @throws DukeException cause by exception handled in DukeException class.
     */
    public Todo(String description) throws DukeException {
        super();
        try {
            int startingIndex = calculateStartingIndex(description, TASK_KEYWORD);
            taskDescription = getSubString(description, startingIndex);
            if (taskDescription == "") {
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
        taskDescription = todoDescription;
    }
    private int calculateStartingIndex(String description, String wordSlicer) {
        return description.indexOf(wordSlicer) + wordSlicer.length();
    }
    private String getSubString(String taskDescription, int startIndex, int ... endIndex) {
        if (endIndex != null) {
            return taskDescription.substring(startIndex, endIndex[0]);
        }
        return taskDescription.substring(startIndex);
    }
    /**
     * Prints out the duke.task.
     *
     * @return string format of the todo duke.task,
     * consisting of the duke.task marker "T" and duke.task description.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", TASK_MARKER, super.toString(), taskDescription);
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s", TASK_MARKER, getStatusIcon() == " " ? 1 : 0,
            taskDescription);
    }

    /**
     * Returns duke.task marker.
     *
     * @return a one character string that is a marker for this duke.task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }
}
