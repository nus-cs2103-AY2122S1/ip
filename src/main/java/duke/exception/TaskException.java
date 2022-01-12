package duke.exception;

import java.util.List;
import java.util.ArrayList;

import duke.task.Task;

/**
 * The TaskException class encapsulates information
 * and methods pertaining to exceptions relating to tasks in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class TaskException extends DukeException {
    private Task task;
    /**
     * Creates and initalizes a new TaskException with the task.
     *
     * @param task The task that has failed to initialise.
     * @return A new TaskException object.
     */
    public TaskException(Task task) {
        super(String.format("Invalid %s-task creation syntax.", task.getType()));
        this.task = task;
    }

    /**
     * Returns the formatted lines of messages that should be
     * displayed when this TaskException has been thrown. Correct format
     * to input commands to Duke to create this tasks is added.
     *
     * @return A List of Strings that should be output as help messages.
     */
    public List<String> getHelpMessages() {
        List<String> list = new ArrayList<>();
        list.add(String.format("Use the following format to create %s tasks.", task.getType()));
        list.add("\n  " + task.getFormat() + "\n");
        return list;
    }
}