package duke;

/**
 * General category
 */
public class Task {
    private String name;
    private Boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Mark the task completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * make the string that will be saved to the file
     *
     * @return a string that will be saved to the file
     */
    public String toStorageFormat(){
        return toString();
    }

    public String getName() {
        return name;
    }

    public String toString() {
        if(isDone) {
            return "[X] " + name;
        }
        return "[ ] " + name;
    }
}
