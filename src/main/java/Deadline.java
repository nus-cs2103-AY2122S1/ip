public class Deadline extends Task {

    private String datetime;

    public Deadline(String description, String time) {
        super(description);
        datetime = time;
    }

    @Override
    public String toString() {
        String deadlineMarker = "[D]";

        String timestamp = String.format("(by: %s)", datetime);

        if (isDone) {
            return deadlineMarker + "|" + hasCross + "|" + item + "|" + datetime;
        } else {
            return deadlineMarker + "|" + hasNoCross + "|" + item + "|" + datetime;
        }
    }
}
