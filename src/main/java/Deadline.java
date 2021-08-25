/**
 * A task with a description and an end date/time
 */
public class Deadline extends Task {

    private String endDateTime;

    Deadline(String description, boolean completed, String endDateTime) {
        super(description, completed);
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", endDateTime);
    }

    @Override
    public String encode() {
        return "d" + DukeMemory.DELIMITER
                + (this.isCompleted() ? "1" : "0") + DukeMemory.DELIMITER
                + endDateTime + DukeMemory.DELIMITER
                + this.getDescription();
    }
}
