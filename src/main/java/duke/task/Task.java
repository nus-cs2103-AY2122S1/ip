package duke.task;

public class Task {

    private boolean isCompleted;
    private String taskName;

    /**
     * Constructor for Task class
     *
     * @param taskName name of task
     */
    public Task(String taskName) {
        this.isCompleted = false;
        this.taskName = taskName;
    }

    /**
     * Marks task as isCompleted
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Checks if task is isCompleted
     *
     * @return boolean of whether task is isCompleted
     */
    public boolean checkIfCompleted() {
        return this.isCompleted;
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
     * Returns a visual char of whether the task is isCompleted
     *
     * @param currentTask current task
     * @return visual char of whether the task is isCompleted
     */
    public static String checkIfTaskCompleted(Task currentTask) {
        return currentTask.checkIfCompleted()
                ? "X"
                : " ";
    }
}
