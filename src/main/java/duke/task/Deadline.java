package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byTime;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] parts = by.split(" ");//split along the whitespace to get the integer
            if (parts.length <= 1) {// checking for incomplete prompts
                throw new DukeException();
            }
//        System.out.println(parts[0]);
//        System.out.println(parts[1]);
            String date = parts[0].replace("/", "-");
            this.byDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
            this.byTime = parts[1];
        } catch (DateTimeParseException e) {
            System.out.println("Please input ur date in dd/mm/yyyy format");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + byTime + ")";
    }
}
