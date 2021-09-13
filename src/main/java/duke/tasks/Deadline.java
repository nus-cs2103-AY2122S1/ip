package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import duke.exceptions.WrongInputException;


public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name.trim());
        this.by = by;
    }

    public static Deadline createDeadline(String name, String by) throws WrongInputException {
        if (by.equals("")) {
            return new Deadline(name, by);
        } else {
            return new Deadline(name, processDateAndTime(by));
        }
    }

    private static String processDateAndTime(String dateAndTime) throws WrongInputException {
        String input = dateAndTime.trim();
        String[] list = input.split(" ");
        String output = "";

        if (list.length != 2) {
            throw new WrongInputException("You must enter Date and Time in this format: dd/MM/yyyy HHmm");
        }

        String date = list[0];
        String time = list[1];

        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            DateTimeFormatter resultDateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
            DateTimeFormatter resultTimeFormat = DateTimeFormatter.ofPattern("HH.mm a");

            output = " " + LocalDate.parse(date, dateFormat).format(resultDateFormat).toString() + ", "
                    + LocalTime.parse(time, timeFormat).format(resultTimeFormat).toString();

            return output;
        } catch (DateTimeParseException e) {
            throw new WrongInputException("You must enter Date and Time in this format: dd/MM/yyyy HHmm");
        }
    }

    @Override
    public Task snoozeTask() {
        String[] list = this.by.split(", ");
        String date = list[0].trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String newDate = LocalDate.parse(date, dateFormat).plus(1, ChronoUnit.WEEKS).format(dateFormat);
        this.by = " " + newDate + ", " + list[1];

        return this;
    }


    @Override
    public String toString() {
        if (!by.equals("")) {
            return "[D]" + super.toString() + " (by:" + by + ")";
        } else {
            return "[D]" + super.toString();
        }
    }
}
