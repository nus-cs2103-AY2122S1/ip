package duke;

public class Deadline extends Task{
    private final DateString dateLine;

    public Deadline(String description) {
        super(description);
        if (description.isBlank()) {
            throw new IllegalArgumentException("Description of Deadline cannot be empty!");
        }
        this.type = 'D';
        this.dateLine = new DateString("");
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.type = 'D';
        this.dateLine = new DateString(deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateLine.toString() + ")";
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }



}
