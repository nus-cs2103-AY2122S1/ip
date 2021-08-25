public class Deadline extends Task{
    private final String dateTime;

    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by ") - 1));
        this.dateTime = description.substring(description.indexOf(" /by") + 5);
    }

    @Override
    public String getTaskInfo() {
        return "D" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }
}
