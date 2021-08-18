public class Deadline extends Task{

    private String deadline;

    public Deadline(String name, String deadline) {
           super(name);
           this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }
}
