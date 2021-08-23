import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a Task that recognises /at time
 *
 * @author Chen Yanyu
 */

class Event extends Task {
    private final String task;
    LocalDate time;

    public Event(String description) throws EmptyDescriptionException, WrongFormatException {
        super(processEventDescription(description));
        String[] descriptionTime = description.split(" /at ");
        this.task = descriptionTime[0];
        this.time = LocalDate.parse(descriptionTime[1]);
    }

    private static String processEventDescription(String description) throws WrongFormatException {
        String[] descriptionTime = description.split(" /at ");
        if (description.trim().equals("/at") || description.isBlank()) {
            return "";
        } else if (descriptionTime.length < 2) {
            throw new WrongFormatException("event <description> /at <yyyy-mm-dd>");
        } else if (descriptionTime[0].isBlank() || descriptionTime[1].isBlank()) {
            return "";
        } else {
            return descriptionTime[0];
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * return the save string arrays for file writing
     *
     * @return the array of Strings representing the task
     */
    public String[] saveStrings() {
        String isDone = this.getIsDone() ? "1" : "0";
        return new String[]{"E", isDone, this.task, time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))};
    }
}
