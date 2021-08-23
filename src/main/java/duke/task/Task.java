package duke.task;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level-9. Find
 *
 * Description:
 * Encapsulates the Task Class which contains a task description
 * and their completion status
 *
 * @author Keith Tan
 */
public class Task {

    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Sets Duke.util.Duke.task.Task Completion Status to true and returns true if successfully marked
     *
     * @return boolean returns true if task successfully marked and false if task
     *                 already marked
     *
     */
    public boolean completeTask() {

        if (this.isCompleted) {
            return false;
        } else {
            this.isCompleted = true;
            return true;
        }

    }

    /**
     * Getter that returns the completion status of the task
     *
     * @return boolean Returns the completion status of the task
     */
    public boolean isCompleted() {

        return this.isCompleted;
    }

    /**
     * Getter that returns the description of the task
     *
     * @return String Returns the description of the task
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * Getter that returns the description of the task
     *
     * @return boolean Returns true if search term exists in the description
     */
    public boolean checkTerm(String searchTerm) {
        String[] descriptionArr = this.description.split(" ");
        for (String word : descriptionArr) {
            if (word.equals(searchTerm)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String taskStatus = isCompleted ? "X" : " ";
        return "[" + taskStatus + "]" + " " + description;
    }

}