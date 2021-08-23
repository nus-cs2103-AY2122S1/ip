package tasks;

import utils.StorageParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String time) {
        super(description);
        this.taskIcon = "D";
        this.by = LocalDate.parse(time);
    }

    private String getFormattedTime() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public Deadline(StorageParser storageParser) {
        super(storageParser);
        this.by = LocalDate.parse(storageParser.getTime());
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedTime() +")";
    }

    @Override
    public String saveFormat() {
        return String.join(Task.delimiter,
                            super.saveFormat(),
                            this.by.toString());
    }
}
