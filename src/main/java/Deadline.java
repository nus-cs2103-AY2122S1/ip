public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
        int index = description.indexOf("/by");
        this.description = description.substring(0, index) + "(by:" + description.substring(index + 3) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}