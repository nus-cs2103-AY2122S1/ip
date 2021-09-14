package duke.task;

public class Task {

    private boolean completed;
    private String taskName;

    /**
     * Constructor for Task class
     *
     * @param taskName name of task
     */
    public Task(String taskName) {
        this.completed = false;
        this.taskName = taskName;
    }

    /**
     * Marks task as completed
     */
    public void markAsCompleted() {
        this.completed = true;
    }

    /**
     * Checks if task is completed
     *
     * @return boolean of whether task is completed
     */
    public boolean checkIfCompleted() {
        return this.completed;
    }

    /**
     * Returns the name of the task
     *
     * @return name of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName();
    }

    /**
     * Returns a visual char of whether the task is completed
     *
     * @param currentTask current task
     * @return visual char of whether the task is completed
     */
    public static String checkIfTaskCompleted(Task currentTask) {
        return currentTask.checkIfCompleted()
                ? "X"
                : " ";
    }
}
