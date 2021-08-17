public class Deadline extends Task{

    public Deadline(String description, String deadline) {
        super(description, "D", deadline);
    }

    @Override
    public String toString() {
        return '[' + this.taskType + ']' + '[' + getStatusIcon() + ']' + ' ' + this.description
                + String.format("(by: %s)", this.time);
    }
}
