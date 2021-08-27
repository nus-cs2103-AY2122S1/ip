package main.java;

/**
 * Task class that encapsulates the individual task passed into the bot.
 */
public class Task {

    /**
     * Variable that holds the task input as String
     */
    protected String task;

    /**
     * Variable that holds the task completion state as boolean
     */
    protected boolean done;

    /**
     * Constructor for the Task class
     * Default: not done
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Constructor for the Task class
     * According to specified state
     */
    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    public String getTaskState() {
        return this.done ? "[X] " + this.task : "[ ] " + this.task;
    }

    /**
     * Toggles the current state by negating the boolean state value. (Done vs not done)
     */
    public void markAsDone() {
        this.done = true;
    }

    public static Task parseFromStorage(String task) {
        String[] splitTask = task.split(",");
        String taskType = splitTask[0];

        switch (taskType) {
            case "T":
                return new TodoTask(splitTask[2], splitTask[1].equals("1"));
            case "D":
                return new DeadlineTask(splitTask[2], splitTask[3], splitTask[1].equals("1"));
            case "E":
                return new EventTask(splitTask[2], splitTask[3], splitTask[1].equals("1"));
            default:
                return new Task("");
        }
    }

    public String convertFormat() {
        return "";
    }
}
