import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String atString;
    private LocalDate atDate;
    public Event(String description, LocalDate at) {
        super(description);
        this.atDate = at;
    }

    public Event(String description, String at) {
        super(description);
        this.atDate = LocalDate.parse(at);
    }

    public Event(String isDone, String description, String at) {
        super(description, isDone.equals("1"));
        this.atDate = LocalDate.parse(at);
    }

    public static String extractTaskDescription(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 2);
        String task_type = contents[0];
        String description = "";

        if (contents.length != 2) {
            throw new UnclearInstructionException("OOPS!!! The description of an event cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");
        
        description = text.substring(istart + 1, iend - 1);
        
        if (description.equals("")) {
            throw new UnclearInstructionException("OOPS!!! The description of an event cannot be empty.");
        }
        return description;
    }

    public static String extractTaskTime(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 2);
        String task_type = contents[0];
        if (contents.length != 2) {
            throw new UnclearInstructionException("OOPS!!! The description of an event cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");
        String time = text.substring(iend + 4);

        if (time.equals("")) {
            throw new UnclearInstructionException("OOPS!!! The time of an event cannot be empty.");
        }
        return time;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "|" + atDate;
    }
}