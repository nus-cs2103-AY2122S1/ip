package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DateTask extends Task {
    private LocalDate date;
    private String dateString;

    public DateTask(String description, String dateString, boolean isDone) throws DukeException {
        super(description, isDone);
        assignDate(dateString);
        this.dateString = dateString;
    }

    private void assignDate(String dateString) throws DukeException {
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops!!! Date should be in this format: dd/MM/yyyy");
        }
    }

    public String getDateString() {
        return dateString;
    }

    public String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateTask) || !super.equals(obj)) {
            return false;
        }
        DateTask other = (DateTask) obj;
        return dateString.equals(other.getDateString());
    }

}
