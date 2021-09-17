package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Class that represent a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime date;
    public static final String inputExample = "deadline return book /by 3/4/2021 400";
    public static final String syntax = "deadline {description of deadline} /by {dd/MM/yyyy hhmm}";

    private Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.date = dateTime;
    }
    /**
     * Makes a deadline object.
     * @param input description of deadline.
     * @return deadline object.
     * @throws DukeException if input is invalid.
     */
    public static Deadline of(String input) throws DukeException {
        String[] eachWord = input.split("/by");
        checkForEmptyDescription(eachWord);
        String dateDescription = eachWord[1];
        String[] dateSplitBySpace = dateDescription.split(" ");
        checkForEmptyDate(dateSplitBySpace);
        String[] dateArr = dateSplitBySpace[1].split("/");
        try {
            return getDeadline(eachWord[0], dateSplitBySpace[2], dateArr);
        } catch (NumberFormatException e) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Deadline.inputExample);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date inputed. Please check that the date is correct\nEg."
                    + Deadline.inputExample);
        } catch (Exception e) {
            assert false : "Unknown exception being thrown in Deadline";
        }

        assert false : "Error in of function in Deadline";
        return null;
    }

    private static Deadline getDeadline(String description, String s, String[] dateArr) {
        int year = Integer.parseInt(dateArr[2]);
        int month = Integer.parseInt(dateArr[1]);
        int date = Integer.parseInt(dateArr[0]);
        int hour = Integer.parseInt(s.substring(0, s.length() - 2));
        int min = Integer.parseInt(s.substring(s.length() - 2));
        LocalDateTime dateTime = LocalDateTime.of(year, month, date, hour, min);
        return new Deadline(description, dateTime);
    }

    private static void checkForEmptyDate(String[] dateSplitBySpace) throws DukeException {
        if (dateSplitBySpace.length < 3) {
            throw new DukeException("Enter the date for this event in DD/MM/YYYY HHMM format\nEg."
                    + Deadline.inputExample);
        }
    }

    private static void checkForEmptyDescription(String[] eachWord) throws DukeException {
        if (eachWord.length == 1 || eachWord[1].length() == 0 || eachWord[1].equals(" ")) {
            throw new DukeException("The date for this event cannot be empty. Type date after /by\nEg."
                    + Deadline.inputExample);
        }
    }

    /**
     * Returns string representation of deadline.
     *
     * @return string representation of deadline.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + String.format("(by:%s)", this.date));
    }

    /**
     * Returns string representation of deadline to be saved in hard disk.
     * @return String representation to be saved in hard disk.
     */
    @Override
    public String typeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String formatDateTime = this.date.format(formatter);
        return "deadline" + Task.SEP + super.toSaveInFile("/by " + formatDateTime);
    }
}
