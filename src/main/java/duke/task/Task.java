package duke.task;

import duke.general.TaskType;

/**
 * Encapsulates the name and completion status of the task.
 */

public abstract class Task {
    private final String name;
    private boolean completed;

    /**
     * Constructs a duke.task.Task object that contains the name and completion status set to false.
     * @param name The name of the duke.task.Task.
     */
    public Task(String name) {
        this.name = name;
        completed = false;
    }

    /**
     * Prints out the message to be shown when a new task has been added to the duke.task.Task list.
     * @param size Total number of tasks in the list
     */
    public String addResponse(int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        System.out.println("Now you have " + size + " tasks in your list.");
        return "Got it. I've added this task: \n" + this + "\n"
                + "Now you have " + size + " tasks in your list.";
    }

    public String getName() {
        return this.name;
    }

    public String getCompletion() {
        return (completed ? "X" : " ");
    }

    /**
     * Toggles the completion status.
     */
    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    /**
     * Obtains the type of the task
     * @return String type of task
     */
    public abstract String getType();

    /**
     * Obtain the TaskType of the task
     * @return a Tasktype
     */
    public abstract TaskType getTaskType();

    /**
     * Obtains the additional information of the task. If not additional information, just return empty string
     * @return String containing additional info on task
     */
    public abstract String getInfo();

    public abstract Task duplicate();

    /**
     * Converts task into a string
     * @return String containing completion status and name.
     */
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + this.name;
    }
}
