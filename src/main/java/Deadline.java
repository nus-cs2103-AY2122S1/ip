public class Deadline extends Task {
    private String marker = "[D]";
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

//    public String getMarker() {
//        return this.marker;
//    }

    @Override
    public String toString() {
        return this.marker + super.toString() + " (by:" + deadline + ")";
    }
}
