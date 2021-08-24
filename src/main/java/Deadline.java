import java.util.InputMismatchException;

public class Deadline extends Task {
    private String time;

    public static Deadline createDeadline(String desc) throws InputMismatchException {
        if (desc.contains("/by")) {
            String[] arr = desc.split("/by");
            return new Deadline(arr[0].trim(), arr[1].trim());
        } else {
            throw new InputMismatchException();
        }
    }

    public static Deadline createDeadline(String desc, boolean isDone) throws InputMismatchException {
        if (desc.contains("by")) {
            int i = desc.indexOf('(');
            return new Deadline(desc.substring(0, i - 1),
                    desc.substring(i + 5, desc.length() - 1),
                    isDone);
        } else {
            throw new InputMismatchException();
        }
    }

    private Deadline(String details, String time) {
        super(details);
        this.time = time;
    }

    private Deadline(String details, String time, boolean isDone) {
        super(details, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
