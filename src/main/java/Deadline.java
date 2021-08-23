import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by);
    }

    public Deadline(String isDone, String description, String by) {
        super(description, isDone.equals("1"));
        this.byDate = LocalDate.parse(by);
    }

    public static String extractTaskDescription(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 2);
        String task_type = contents[0];
        String description = "";

        if (contents.length != 2) {
            throw new UnclearInstructionException("OOPS!!! The description of a " + task_type + " cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");

        description = text.substring(istart, iend);

        if (description.equals("")) {
            throw new UnclearInstructionException("OOPS!!! The description of a deadline cannot be empty.");
        }
        return description;
    }

    public static String extractTaskTime(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 2);
        String task_type = contents[0];
        if (contents.length != 2) {
            throw new UnclearInstructionException("OOPS!!! The description of a deadline cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");
        String time = text.substring(iend + 4);

        if (time.equals("")) {
            throw new UnclearInstructionException("OOPS!!! The time of a deadline cannot be empty.");
        }
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + byDate;
    }
}