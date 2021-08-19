import java.util.Optional;

public class Deadline extends Task{

    private Deadline(String description, String deadline) {
        super(description, "D", deadline);
    }

    public static Deadline create(String description, String time) {
        return new Deadline(description, time);
    }

    @Override
    public String toString() {
        return '[' + this.taskType + ']' + '[' + getStatusIcon() + ']' + ' ' + this.description
                + String.format("(by:%s)", this.time);
    }
}
