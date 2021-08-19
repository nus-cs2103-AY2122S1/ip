public class Deadline extends Task {

    private String deadline;

    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        this.deadline = description.split(" /by ")[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
