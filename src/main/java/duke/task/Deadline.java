package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byTime;

    public Deadline(String description, String by) throws DukeException, DateTimeParseException{
        super(description);
            String[] parts = by.split(" ");//split along the whitespace to get the integer
            if (parts.length <= 1) {// checking for incomplete prompts, no time provided
                throw new DukeException();
            }
//        System.out.println(parts[0]);
//        System.out.println(parts[1]);
            String date = parts[0].replace("/", "-");
            this.byDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
            this.byTime = parts[1];

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + byTime + ")";
    }
}
