// tasks that need to be done before a specific date/time
public class Deadline extends Task {
    protected String description;
    protected String deadline;

    public Deadline(String description) {
        super(description);
        this.description = description;

    }

    public String getTask() {
        String[] splitted = description.split("/by ", 2);
        String text = splitted[0];
        deadline = splitted[1];
        return "[D]" + "[" + super.getStatusIcon() + "] " + text + "(by: " + deadline + ")";
    }
}
