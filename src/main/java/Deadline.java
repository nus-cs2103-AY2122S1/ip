public class Deadline extends Task{
    private String dateTime;

    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/by ")));
        this.dateTime = description.substring(description.indexOf("/by ") + 4);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateTime + ")";
    }
}
