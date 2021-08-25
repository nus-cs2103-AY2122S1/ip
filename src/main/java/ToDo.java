/**
 * A task with a description and no date/time
 */
public class ToDo extends Task {
    ToDo(String description, boolean completed) {
        super(description, completed);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encode() {
        return "t" + DukeMemory.DELIMITER
                + (this.isCompleted() ? "1" : "0") + DukeMemory.DELIMITER
                + this.getDescription();
    }
}
