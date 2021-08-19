/**
 * Task class. List HAS-A Task (encapsulation).
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-4
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
        Duke.divider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        Duke.divider();
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
