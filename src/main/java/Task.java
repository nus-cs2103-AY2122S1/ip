/**
 * Task class. List HAS-A Task (encapsulation).\
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-3
 */

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + getDescription());
    }

}
