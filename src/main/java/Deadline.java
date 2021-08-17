public class Deadline extends Entry {

    private String deadline;

    Deadline() {
        super();
    }

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
