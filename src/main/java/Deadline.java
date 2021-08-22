import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    private String by;
    private static final char TASK_LETTER = 'D';

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)", Deadline.TASK_LETTER, super.toString(), this.by);
    }

    @Override
    public String stringToStore() {
        return Deadline.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.by + "\n";
    }
}