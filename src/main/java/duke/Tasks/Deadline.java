package duke.Tasks;

import duke.Exceptions.WrongInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            DateTimeFormatter dateF = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeF = DateTimeFormatter.ofPattern("HHmm");
            DateTimeFormatter resultDF = DateTimeFormatter.ofPattern("dd MMM yyyy");
            DateTimeFormatter resultTF = DateTimeFormatter.ofPattern("HH.mm a");

            output = " " + LocalDate.parse(date, dateF).format(resultDF).toString() + ", "
                    + LocalTime.parse(time, timeF).format(resultTF).toString();
            return output;
        } catch (DateTimeParseException e) {
            throw new WrongInputException("You must enter Date and Time in this format: dd/MM/yyyy HHmm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
