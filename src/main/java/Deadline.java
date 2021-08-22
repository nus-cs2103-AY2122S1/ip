/**
 * Deadline class.
 * Used to represent a deadline task.
 *
 * @author KelvinSoo
 * @version Level-4
 *
 */
public class Deadline extends Task {
    private String dateLine;

    public Deadline(String description, String dateLine) {
        super(description);
        this.dateLine = dateLine;
    }

    public Deadline(String description, String dateLine, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.dateLine = dateLine;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getMetaData() {
        return String.format("D|%s|%s", super.getMetaData(), dateLine);
    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", super.getDescription(), this.dateLine);
    }

}
