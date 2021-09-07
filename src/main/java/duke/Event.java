package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a Task that recognises /at time
 *
 * @author Chen Yanyu
 */

class Event extends Task {
    public static final String DELIMINATOR = "/at";
    public static final String TYPE = "event";
    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time.format(DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }

    /**
     * return the save string arrays for file writing
     *
     * @return the array of Strings representing the task
     */
    public String[] getAsSaveStrings() {
        String isDone = this.getIsDone() ? "1" : "0";
        return new String[]{"E", isDone, this.getDescription(), time.format(DateTimeFormatter.ISO_LOCAL_DATE)};
    }
}
