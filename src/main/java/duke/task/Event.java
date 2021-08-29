package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Class that represent a event task.
 */
public class Event extends Task {
    private final LocalDateTime date;
    private static final String inputExample = " event my birtday /at 01/01/2000 1400";

    private Event(String description, LocalDateTime dateTime) {
        super(description);
        this.date = dateTime;
    }
    /**
     * Makes a event object.
     * @param input description of event.
     * @return event object.
     * @throws DukeException if input is invalid.
     */
    public static Event of(String input) throws DukeException {
        String[] eachWord = input.split("/at");
        if (eachWord.length == 0 || eachWord[0].length() == 0 || eachWord[0].equals(" ")) {
            throw new DukeException("Description cannot be empty. Type description before /at\nEg."
                    + Event.inputExample);
        }
        if (eachWord.length == 1 || eachWord[1].length() == 0 || eachWord[1].equals(" ")) {
            throw new DukeException("The date for this event cannot be empty. Type date after /at\nEg."
                    + Event.inputExample);
        }
        String dateDescription = eachWord[1];
        String[] dateSplitBySpace = dateDescription.split(" ");
        if (dateSplitBySpace.length < 3) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Event.inputExample);
        }
        String[] dateArr = dateSplitBySpace[1].split("/");
        if (dateArr.length < 3) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Event.inputExample);
        }
        try {
            int year = Integer.parseInt(dateArr[2]);
            int month = Integer.parseInt(dateArr[1]);
            int date = Integer.parseInt(dateArr[0]);
            int hour = Integer.parseInt(dateSplitBySpace[2].substring(0, dateSplitBySpace[2].length() - 2));
            int min = Integer.parseInt(dateSplitBySpace[2].substring(dateSplitBySpace[2].length() - 2));
            LocalDateTime dateTime = LocalDateTime.of(year, month, date, hour, min);
            return new Event(eachWord[0], dateTime);
        } catch (NumberFormatException e) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Event.inputExample);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date inputed. Please check that the date is correct\nEg."
                    + Event.inputExample);
        }
    }

    /**
     * Returns string representation of event object.
     * @return string representation of event object.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + String.format("(at:%s)", this.date));
    }

    /**
     * Returns string representation of event object to be saved in hard disk.
     * @return string representation of event object to saved in hard disk.
     */
    @Override
    public String typeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String formatDateTime = this.date.format(formatter);
        return "event" + Task.SEP + super.toSaveInFile("/at " + formatDateTime);
    }
}
