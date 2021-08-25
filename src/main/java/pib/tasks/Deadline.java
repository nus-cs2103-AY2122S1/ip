package pib.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pib.Ui;
import pib.pibexception.PibException;

/**
 * Deadline task which contains the task description, and the date of the deadline
 */
public class Deadline extends Task {

    private String date;
    private String time;

    /**
     * A public constructor to create a Deadline task
     *
     * @param description description of the deadline task
     * @param date        the date stated after "/by " portion
     */
    private Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    private Deadline(String description, int isDone, String date, String time) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    public static Deadline createDeadline(String details) throws PibException {
        try {
            int byIndex = details.indexOf("/by ");
            String description = details.substring(0, byIndex).trim();
            if (description.isBlank()) {
                Ui.printError("empty-task-description");
                throw new PibException("Task description can't be blank");
            }
            String[] dateTime = details.substring(byIndex + 4).trim().split(" ");
            String date = LocalDate.parse(dateTime[0].trim()).format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            String time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
            return new Deadline(description, date, time);
        } catch (IndexOutOfBoundsException e) {
            Ui.printError("d-wrong-format");
            throw new PibException("Wrong command format");
        } catch (DateTimeParseException e) {
            Ui.printError("wrong-datetime-format");
            throw new PibException("Wrong date-time format");
        }
    }

    public static Deadline createDeadline(String description, int isDone, String date, String time) {
        return new Deadline(description, isDone, date, time);
    }

    public String toDataString() {
        return "D," + getIsDone() + "," + getDescription() + "," + date + "," + time + System.lineSeparator();
    }

    /**
     * A public toString method to add the task type [D] in front of the checkbox, and the date behind the task description
     *
     * @return the string representation of a deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ", " + this.time + ")";
    }
}
