/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 3. Mark As Done
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
     * Sets Task Completion Status to true
     *
     */
    public void completeTask() {

        this.completed = true;

    }

    @Override
    public String toString() {
        String taskStatus = completed ? "X" : " ";
        return "[" + taskStatus + "]" + " " + description;
    }

}