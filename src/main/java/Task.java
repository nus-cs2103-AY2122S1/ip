/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 6. Delete
 *
 * Description:
 * Encapsulates the Task Class which contains a task description
 * and their completion status
 *
 * @author Keith Tan
 */
public class Task {

    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Sets Task Completion Status to true and returns true if successfully marked
     *
     * @return boolean returns true if task successfully marked and false if task
     *                 already marked
     *
     */
    public boolean completeTask() {

        if (this.completed) {
            return false;
        } else {
            this.completed = true;
            return true;
        }

    }

    /**
     * Getter that returns the completion status of the task
     *
     * @return boolean Returns the completion status of the task
     */
    public boolean isCompleted() {

        return this.completed;
    }

    /**
     * Getter that returns the description of the task
     *
     * @return String Returns the description of the task
     */
    public String getDescription() {

        return this.description;
    }

    @Override
    public String toString() {
        String taskStatus = completed ? "X" : " ";
        return "[" + taskStatus + "]" + " " + description;
    }

}