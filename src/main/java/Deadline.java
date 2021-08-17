public class Deadline extends Task {
    private String name;
    private String deadline;

    Deadline(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public String logo() {
        return "[D]";
    }

    @Override
    public String toString() {
        return this.name + " (by: " + this.deadline + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }
}
