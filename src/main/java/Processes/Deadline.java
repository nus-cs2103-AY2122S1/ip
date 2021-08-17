package Processes;

public class Deadline extends Task {

    public Deadline(String description, String remarks) {
        super(description, remarks);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.description + " (by: " + remarks + ")";
        }
        return "[D][ ] " + this.description + " (by: " + remarks + ")";
    }
}