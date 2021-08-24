package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class encapsulates a Task to be done by a given deadline.
 */
public class Deadline extends Task {

    private String dlineString;
    private LocalDate dlineDate;

    public Deadline(String input) {
        super(input.substring(0, input.indexOf("/by ") - 1));
        String dline = input.substring(input.indexOf("/by ") + 4);
        try {
            this.dlineDate = LocalDate.parse(dline);
        } catch (DateTimeParseException e) {
            this.dlineString = dline;
        }
    }

    public Deadline(String name, String dline) {
        super(name);
        try {
            this.dlineDate = LocalDate.parse(dline);
        } catch (DateTimeParseException e) {
            this.dlineString = dline;
        }
    }

    /**
     * Returns a String representing the Deadline Task.
     *
     * @return a String representing the Deadline Task.
     */
    @Override
    public String toString() {
        String msg = "[D]" + super.toString() + " (by: ";
        if (dlineString == null) {
            msg = msg + dlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
        } else {
            msg = msg + dlineString + ")";
        }
        return msg;
    }

    /**
     * Returns a String representing the Deadline Task to be saved in the taskList.txt file.
     *
     * @return a String representing the Deadline Task to be saved in the taskList.txt file.
     */
    public String printToFile() {
        String msg = "D | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | ";
        if (dlineString == null) {
            msg = msg + dlineDate.toString();
        } else {
            msg = msg + dlineString;
        }
        return msg;
    }
}
