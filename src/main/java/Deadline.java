public class Deadline extends Task {
    private String time;

    public static Deadline createDeadline(String desc) {
        String arr[] = desc.split("/by");
        return new Deadline(arr[0].trim(), arr[1].trim());
    }

    private Deadline(String details, String time) {
        super(details);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
