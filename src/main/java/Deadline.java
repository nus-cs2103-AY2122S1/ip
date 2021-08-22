public class Deadline extends Task{

    protected String by;

    /**
     * Constructor to create a Deadline.
     * @param description Description of the Deadline task.
     * @param by Date of when Deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the given Deadline into an appropriate format for txt file.
     * @return a String of the Deadline for input into a txt file.
     */
    @Override
    public String toTxt() {
        return String.format("D | %d | %s | %s", super.getIsDone() ? 1 : 0, super.getDescription(),
                this.by + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
