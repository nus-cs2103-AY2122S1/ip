package duke.task;

import duke.exception.DukeException;

/**
 * <h1> Task </h1>
 * Encapsulates a task that can be marked as done and convert itself
 * to text to be saved and vice versa.
 *
 * @author Clifford
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskSymbol;
    protected final static String divider = ",";
    protected final static String doneStatus = "[X]";
    protected final static String notDoneStatus = "[ ]";

    public Task(String description, String taskSymbol) {
        this.description = description;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
    }

    /**
     * Converts a text from storage file to a Task, otherwise throws DukeException.
     *
     * @param text the text representation of a task in storage file
     * @return the corresponding type of task based on the text provided
     * @throws DukeException if the text representation in storage file does not match any tasks
     */
    public static Task createTaskFromText(String text) throws DukeException {
        String[] taskInformation = text.split(divider);
        String taskType = taskInformation[0].trim();
        String taskStatus = taskInformation[1].trim();

        switch(taskType) {
            case "[T]":
                Task todo = new Todo(taskInformation[2]);
                changeStatusFromText(todo, taskStatus);
                return todo;
            case "[D]":
                Task deadline = new Deadline(taskInformation[2], taskInformation[3]);
                changeStatusFromText(deadline, taskStatus);
                return deadline;
            case "[E]":
                Task event = new Event(taskInformation[2], taskInformation[3]);
                changeStatusFromText(event, taskStatus);
                return event;
            default:
                throw new DukeException("Task symbol from text is not recognised.");
        }
    }

    private static Task changeStatusFromText(Task task, String symbol) {
        if(symbol.equals(doneStatus)) {
            task.markAsDone();
        }
        return task;
    }

    public String getTaskSymbol() {
        return taskSymbol;
    }

    public String getDivider() {
        return divider;
    }

    /**
     * converts a Task object to a formatted text to be saved in storage.
     *
     * @return text representation of Task in storage files.
     */
    public String convertToText() {
        StringBuilder sb = new StringBuilder();
        return sb
            .append(getTaskSymbol())
            .append(divider)
            .append(getStatusIcon())
            .append(divider)
            .append(description)
            .toString();
    }

    /**
     * produces a graphical icon of whether a task is done or not.
     *
     * @return a graphical icon of whether a task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? doneStatus : notDoneStatus);
    }

    /**
     * Mark a task as done and leaves a marked task unchanged.
     *
     * @return true if the task is initially not done and marked as done.
     */
    public boolean markAsDone() {
        boolean alreadyDone = isDone;
        isDone = true;
        return !alreadyDone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatusIcon()).append(" ").append(this.description);
        return sb.toString();
    }
}
