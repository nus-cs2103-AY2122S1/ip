package duke.tasktype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.Duke;
import duke.Ui;
import duke.exception.WrongCommandFormatException;

/**
 * Class to represent a task with a deadline.
 * Has an additional component of a deadline.
 *
 * @author Houten Teo
 * @version CS2103T week 6
 */
public class Deadline implements Task {

    private final String taskType = "DEADLINE";
    private String command;
    private String description = " ";
    private boolean isDone;
    private LocalDate deadline;
    private DateTimeFormatter currentFormat = DateTimeFormatter.ofPattern(Duke.getFormat());

    /**
     * Constructor for the deadline class.
     * @param description the description of the task.
     * @param isDone whether the task is completed .
     * @throws WrongCommandFormatException Thrown when the user enters the
     *                                      command with the wrong format.
     */
    public Deadline(String description, boolean isDone) throws WrongCommandFormatException {
        Scanner s = new Scanner(description);
        this.command = description;
        while (s.hasNext()) {
            String next = s.next();
            if (next.equals("/at")) {
                throw new WrongCommandFormatException("Wrong keyword used. Please try again with /by");
            } else if (next.equals("/by")) {
                checkTimeframe(s);
            } else {
                this.description += next + " ";
            }
        }

        if (this.description.equals(" ")) {
            throw new WrongCommandFormatException(
                    "No task specified. Please specify a task before `/by`"
            );
        } else if (this.deadline == null) {
            throw new WrongCommandFormatException(
                    "No deadline specified. Please specify a deadline after `/by`"
            );
        } else {
            this.isDone = isDone;
        }
    }

    /**
     * Checks if there is a  timeframe specified after the "/by" keyword.
     * In the case of reading from the data file, this method also checks for
     * the format of the date when the data file was written and saves the date accordingly.
     * @param s The scanner containing the timeframe component of the command
     * @throws WrongCommandFormatException
     */
    public void checkTimeframe(Scanner s) throws WrongCommandFormatException {
        String date;
        if (s.hasNext()) {
            date = s.next();
        } else {
            throw new WrongCommandFormatException(
                    "No deadline specified. Please specify a deadline after `/by`"
            );
        }

        try {
            parseTimeFrame(s, date);
        } catch (DateTimeParseException ex) {
            throw new WrongCommandFormatException(
                    "Wrong deadline format specified. \n"
                            + "Current format setting: "
                            + Duke.getFormat()
                            + "\n"
                            + "Please try again or consider changing the format "
                            + "settings by using the command `setFormat`"

            );
        }
    }

    /**
     * Parses the dateline and checks if it is in the current specified format.
     * In the case of reading from the data file, checks if the date had been saved in the
     * correct format.
     * @param s The scanner containing the saved format from the data file if applicable.
     * @param date The date to be parsed.
     * @throws DateTimeParseException
     */
    public void parseTimeFrame(Scanner s, String date) throws DateTimeParseException {
        if (s.hasNext()) {
            String formatter = s.next();
            DateTimeFormatter format = DateTimeFormatter.ofPattern(formatter);
            this.deadline = LocalDate.parse(date, format);
        } else {
            this.command += " " + Duke.getFormat();
            this.deadline = LocalDate.parse(date, currentFormat);
        }
    }

    /**
     * Returns the current date format.
     * @return The current format.
     */
    public DateTimeFormatter getCurrentFormat() {
        return this.currentFormat;
    }

    /**
     * Returns the deadline.
     * @return The deadline.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the type of task.
     * @return The string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the type icon.
     * @return The String representation of the icon.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns the type icon.
     * @return The String representation of the icon.
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns the description of the deadline.
     * @return The description of the deadline.
     */
    @Override
    public String getDescription() {
        return this.description;
    }
    /**
     * Returns the string representation of the deadline task.
     * @return The String representation of the deadline task.
     *          The type icon, status icon and description arranged
     *          in a line.
     */
    @Override
    public String toString() {
        String typeIcon = getTypeIcon();
        String statusIcon = getStatusIcon();
        return typeIcon
                + " "
                + statusIcon
                + this.description
                + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern(Duke.getFormat()))
                + ")";
    }

    /**
     * Marks the deadline task as complete.
     * If the task has already been completed, inform the user.
     * Else indicate that the task has been correctly marked.
     */
    @Override
    public String markComplete() {
        if (this.isDone) {
            return Ui.getTaskAlrCompletedMessage(this);
        } else {
            this.isDone = true;
            return Ui.getMarkCompleteDeadlineMessage(this);
        }
    }

    /**
     * Returns the String representation of the deadline to be stored in Data.txt.
     * @return The string representation of the deadline.
     */
    @Override
    public String createData() {
        String data = getTypeIcon()
                    + ""
                    + getStatusIcon()
                    + " "
                    + this.command;
        return data;
    }
    /**
     * Checks if the task has been marked as completed.
     * @return True if the task is completed and false otherwise.
     */
    @Override
    public boolean isDone() {
        return this.isDone;
    }
}
