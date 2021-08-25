package duke.task;

import duke.exceptions.UnclearInstructionException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
    }

    /**
     * Constructor method of Deadline.
     *
     * @param description Description of a deadline.
     * @param by Due time of a deadline.                   
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by);
    }

    /**
     * Another constructor method of Deadline.
     *
     * @param isDone Done indicator of a deadline.  
     * @param description Description of a deadline.
     * @param by Due time of a deadline.                   
     */
    public Deadline(String isDone, String description, String by) {
        super(description, isDone.equals("1"));
        this.byDate = LocalDate.parse(by);
    }

    /**
     * Returns task description of a full command.
     * 
     * @param text  Full command given by user.
     * @return Task description.
     * @throws UnclearInstructionException  If the given command cannot be properly extracted or has empty description.
     */
    public static String extractTaskDescription(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 3);
        String task_type = contents[0];
        String description = "";

        if (contents.length != 3) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of a deadline cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");

        description = text.substring(istart + 1, iend - 1);

        if (description.equals("")) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of a deadline cannot be empty.");
        }
        return description;
    }

    /**
     * Returns due time of a full command.
     *
     * @param text  Full command given by user.
     * @return Task due time.
     * @throws UnclearInstructionException  If the given command cannot be properly extracted or has empty time.
     */
    public static String extractTaskTime(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 3);
        if (contents.length != 3) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of a deadline cannot be extracted properly.");
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
        return "D" + super.toFileString() + "/" + byDate;
    }
}