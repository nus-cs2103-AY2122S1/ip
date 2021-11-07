package duke;
/**
 * Task are work that has a name (that's a string) and a boolean which is true when a task is completed.
 *
 * @author Dominic Siew Zhen Yu
 */
public class Task {
    private String name;
    private boolean completed;

    private String completedIndicator = "[✓]";
    private String uncompletedIndicator = "[✕]";

    /**
     * the constructor of the Task class.
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        completed = false;
    }

    /**
     * returns the name of the Task object, and whether the task is completed or not.
     * @return a string representation of a Task object
     */
    public String printName() {
        String completionStatus = completed ? completedIndicator : uncompletedIndicator;
        return completionStatus + " " + this.name;
    }

    /**
     * returns the name of the tasks
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }



    /**
     * toggles the boolean "completed" when the task is completed,
     * or if the user wishes to indicate that a task is incompleted
     * if it is indicated as otherwise.
     *
     */
    public void toggleComplete() {
        completed = !completed;
    }

    /**
     * returns boolean that represents if the task is completed.
     * @return true if the task is complete and false otherwise
     */
    public boolean isCompleted() {
        return this.completed;
    }
}
