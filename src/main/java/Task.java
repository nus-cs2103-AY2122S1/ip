/**
 * Description:
 * Simple parent class to Event, ToDo, and Deadline.
 *
 * @author Leong Hong Fai
 */

public class Task {
    private String name;
    private boolean completion;

    public Task(String name) {
        this.name = name;
        this.completion = false;
    }

    /**
     * Marks current task as completed.
     */
    public void setCompleted() {
        this.completion = true;
    }


    /**
     *
     * @return X or space depending on completion status of the Task object.
     */
    public String getStatusIcon() {
        return (this.completion
                ?"X"
                :" ");
    }


    /**
     * Simple string representation of Task.
     *
     * @return A string consisting of the information of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}