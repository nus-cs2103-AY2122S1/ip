package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class encapsulates an event that happens at a certain time.
 */
public class Event extends Task {

    public static final String COMMAND_WORD = "event";
    public static final String KEYWORD = "/at";
    public static final String KEYWORD_WITH_SPACE = KEYWORD + " ";

    private String timeString;
    private LocalDate timeDate;

    /**
     * Constructor for an Event task.
     *
     * @param name the given name of the Event.
     * @param time the given time of the Event.
     */
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
