public class Deadline extends Task{
    private String by;

    public Deadline (String taskDetails, String by) {
        super(taskDetails);
        this.by = by;
    }

    public String taskType() {
        return "D";
    }

    @Override
    public String getTaskDetails() {
        return super.getTaskDetails() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}