package virushade.tasks;

import virushade.StringManipulator;
import virushade.VirushadeException;

/**
 * The Task class represents the tasks created by Virushade.
 */
public class Task {

    private boolean isDone;
    private final String taskDescription;

    /**
     * Constructor for a task.
     * @param taskDescription The name of the task.
     */
    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * Parses the input string to create Tasks.
     * Tasks are to be written in the following format:
     * Index. [Task Type][is done] Task name /extra commands (/by for Deadline, /at for Events).
     *
     * @param inputString The input string.
     * @return The tasks that are created from the input string.
     * @throws VirushadeException Thrown when the input string is not of acceptable format.
     */
    public static Task parse(String inputString) throws VirushadeException {
        // Remove the numbering at the start of each line.
        String text = StringManipulator.everythingAfterDot(inputString);

        if (text.length() < 6) {
            throw new VirushadeException("Error: File cannot be parsed.");
        }

        // Sieve out important information for task creation.
        String taskCategory = text.substring(0, 3);
        String taskCompletionStatus = text.substring(3, 6);
        String taskDescription = text.substring(6).trim();
        String[] strings = StringManipulator.slashPartition(taskDescription);

        switch(taskCategory) {
        case "[T]":
            return createToDoTask(taskCompletionStatus, taskDescription);

        case "[E]":
            return createEventTask(taskCompletionStatus, strings);

        case "[D]":
            return createDeadlineTask(taskCompletionStatus, strings);

        default:
            throw new VirushadeException("Error: File cannot be parsed.");
        }
    }

    private static ToDo createToDoTask(String taskCompletionStatus, String taskDescription) throws VirushadeException {
        if (taskCompletionStatus.equals("[x]")) {
            return new ToDo(taskDescription, true);
        } else if (taskCompletionStatus.equals("[ ]")) {
            return new ToDo(taskDescription, false);
        } else {
            throw new VirushadeException("Error: File cannot be parsed.");
        }
    }

    private static Event createEventTask(String taskCompletionStatus, String[] strings) throws VirushadeException {
        if (taskCompletionStatus.equals("[x]")) {
            return new Event(strings[0], strings[1].substring(3), true);
        } else if (taskCompletionStatus.equals("[ ]")) {
            return new Event(strings[0], strings[1].substring(3), false);
        } else {
            throw new VirushadeException("Error: File cannot be parsed.");
        }
    }

    private static Deadline createDeadlineTask(String taskCompletionStatus, String[] strings) throws VirushadeException {
        if (taskCompletionStatus.equals("[x]")) {
            return new Deadline(strings[0], strings[1].substring(3), true);
        } else if (taskCompletionStatus.equals("[ ]")) {
            return new Deadline(strings[0], strings[1].substring(3), false);
        } else {
            throw new VirushadeException("Error: File cannot be parsed.");
        }
    }

    /**
     * @return Updates the task status of completion as done.
     */
    public String completeTask() {
        this.isDone = true;
        return "Nice! I have marked this task as done: \n" + this;
    }

    /**
     * @return The output message when the task is deleted.
     */
    public String deleteMessage() {
        return "Noted. I've removed this task: \n" + this;
    }

    /**
     * @return Task name.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * @return The string representation for the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "x" : " ") + "] " + taskDescription;
    }
}
