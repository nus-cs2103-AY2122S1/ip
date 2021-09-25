package tasks;

import duke.DukeDate;
import exceptions.DukeInvalidStorageTaskException;

/**
 * A class that abstracts a task.
 */
public abstract class Task {

    /**
     * A representation of the 3 types of tasks currently supported by Duke.
     */
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /** Whether the task had been completed or not */
    private boolean isDone = false;
    /** The name of a task as given by the user */
    private final String taskName;
    /** The type of the task */
    private final Type type;


    protected Task(String taskName, Type type) {
        this.taskName = taskName;
        this.type = type;
    }

    /**
     * Returns a description of the task filled with its details.
     *
     * @return The description of the task.
     */
    public abstract String taskDescription();

    /**
     * Converts a task into a specified String format for saving into local storage.
     * The format to save the strings is {type of task} | {is done} | {type of date} |
     * {task name} | {time (if applicable)}.
     * The type of date is based on the static variables in the duke.DukeDate class. It is only applicable
     * for deadline and event tasks. For other tasks with no date, a "0" is used to represent the type
     * of date.
     *
     * @return The String format to save the task as.
     */
    public abstract String taskSaveString();

    /**
     * Marks a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the type of task.
     *
     * @return The required string representation.
     */
    public String taskTypeToString() {
        assert this.type != null : "Error while reading a task";
        if (this.type.equals(Type.TODO)) {
            return "[T]";
        } else if (this.type.equals(Type.EVENT)) {
            return "[E]";
        } else if (this.type.equals(Type.DEADLINE)) {
            // Deadline event
            return "[D]";
        } else {
            // Should not occur since we have checked that the task type is not null and there are only 3
            // possible types of task which we handled above. Hence, a runtime exception is thrown here.
            throw new RuntimeException("A task has no type.");
        }
    }

    /**
     * Returns a task from the String used to saved the task in local storage. When a task is saved to local
     * storage it is saved as a formatted string that stores the information of the task. This methods takes
     * that String and converts it back to the correct task.
     *
     * @param saveString The String that represents the saved task.
     * @return The task converted from saveString.
     * @throws exceptions.DukeInvalidStorageTaskException when unable to read the type of task.
     */
    public static Task storageStringToTask(String saveString) throws DukeInvalidStorageTaskException {

        assert saveString != null : "Error while reading saved tasks";
        String[] strComponents = saveString.split("\\|");
        String typeOfTask = strComponents[0].strip();
        boolean isDone = strComponents[1].strip().equals("1");
        int dukeDateType = Integer.parseInt(strComponents[2].strip());
        String taskName = strComponents[3].strip();

        Task loadedTask;
        switch (typeOfTask) {
        case "T":
            loadedTask = Todo.newTodoTask(taskName);
            break;
        case "D":
            loadedTask = new Deadline(taskName,
                    DukeDate.getDukeDateFromStorage(strComponents[4].strip(), dukeDateType));
            break;
        case "E":
            loadedTask = new Event(taskName,
                    DukeDate.getDukeDateFromStorage(strComponents[4].strip(), dukeDateType));
            break;
        default:
            throw new DukeInvalidStorageTaskException();
        }

        loadedTask.isDone = isDone;
        return loadedTask;
    }

    /**
     * Returns a string representation of the task with its task name and its completion status.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return this.taskTypeToString() + this.getCompletedIcon() + this.taskDescription();
    }

    private String getCompletedIcon() {
        if (this.isDone) {
            return "[" + "\u2713" + "] ";
        } else {
            return "[" + "\u2718" + "] ";
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void revertToUndone() {
        this.isDone = false;
    }
}
