public class Deadline extends Task {
    

    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + super.time.format(TIME_FORMAT) + ")";
    }
}
