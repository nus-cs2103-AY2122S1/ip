package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class encapsulates an event that happens at a certain time.
 */
public class Event extends Task {

    private String timeString;
    private LocalDate timeDate;

    public Event(String input) {
        super(input.substring(0, input.indexOf("/at ") - 1));
        String time = input.substring(input.indexOf("/at ") + 4);
        try {
            this.timeDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            this.timeString = time;
        }
    }

    public Event(String name, String time) {
        super(name);
        try {
            this.timeDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            this.timeString = time;
        }
    }

    /**
     * Returns a String representing the Event Task.
     *
     * @return a String representing the Event Task.
     */
    @Override
    public String toString() {
        String msg = "[E]" + super.toString() + " (at: ";
        if (timeString == null) {
            msg = msg + timeDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
        } else {
            msg = msg + timeString + ")";
        }
        return msg;
    }

    /**
     * Returns a String representing the Event Task to be saved in the taskList.txt file.
     *
     * @return a String representing the Event Task to be saved in the taskList.txt file.
     */
    public String printToFile() {
        String msg = "E | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | ";
        if (timeString == null) {
            msg = msg + timeDate.toString();
        } else {
            msg = msg + timeString;
        }
        return msg;
    }
}
