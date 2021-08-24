/**
 * Task class. List HAS-A Task (encapsulation).
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-7
 */

public class Task {

    private String description;
    private boolean isDone;

    //Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //markAsDone method indicates a task in completed
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription());
    }

}
