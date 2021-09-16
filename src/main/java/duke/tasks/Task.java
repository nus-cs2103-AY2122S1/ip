package duke.tasks;

import duke.DukeException;
import duke.commands.EditCommand;

/**
 * This class represents a Task. It is a superclass from which more specific Task classes will extend from.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Description of task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns an icon representing the status of the task.
     * If the task is done, "X" is returned; else a space " " is returned.
     *
     * @return Icon representing status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task to be stored in a hard disc.
     * This string can be parsed when the Duke chatbot is started in order to load the task into the task list.
     *
     * @return String representation of task to be stored in hard disc.
     */
    public String toFileString() {
        String isDoneString = isDone ? "1" : "0";
        return " | " + isDoneString + " | " + description;
    }

    /**
     * Returns true if the Task's description contains the keyword; otherwise false.
     *
     * @param keyword Keyword that user is searching for.
     * @return true if the Task's description contains the keyword; otherwise false.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns a string representation of a task to be shown to the user.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Checks if an object is equal to this task.
     * The object is equal if it is a task and its description and done status are equal to this task's.
     *
     * @param obj Object to compare to this task.
     * @return Whether object is equal to this task.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return description.equals(other.description) && isDone == other.isDone;
        }
        return false;
    }

    /**
     * Returns updated task using information from the EditCommand.
     *
     * @param edit EditCommand that includes information to update in this Task.
     * @return Updated Task.
     * @throws DukeException If information to update is invalid.
     */
    public abstract Task getUpdatedTask(EditCommand edit) throws DukeException;
}
