package util.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DatedTask extends Task {
    protected LocalDate lDate;

    public DatedTask(String name, LocalDate date) {
        super(name);

        this.lDate = date;
    }

    public String localDate() {
        return lDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }



}
