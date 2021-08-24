import java.util.InputMismatchException;

public class Event extends Task {

    private String time;

    public static Event createEvent(String desc) throws InputMismatchException {
        if (desc.contains("/at")) {
            String[] arr = desc.split("/at");
            return new Event(arr[0].trim(), arr[1].trim());
        } else {
            throw new InputMismatchException();
        }
    }

    public static Event createEvent(String desc, boolean isDone) throws InputMismatchException {
        if (desc.contains("at")) {
            int i = desc.indexOf('(');
            return new Event(desc.substring(0, i - 1),
                    desc.substring(i + 5, desc.length() - 1),
                    isDone);
        } else {
            throw new InputMismatchException();
        }
    }

    private Event(String details, String time) {
        super(details);
        this.time = time;
    }

    private Event(String details, String time, boolean isDone) {
        super(details, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
