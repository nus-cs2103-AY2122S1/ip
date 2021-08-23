package tasks;

import utils.StorageParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, String time) {
        super(description);
        this.taskIcon = "E";
        this.at = LocalDate.parse(time);
    }

    private String getFormattedTime() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public Event(StorageParser storageParser) {
        super(storageParser);
        this.at = LocalDate.parse(storageParser.getTime());
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedTime() +")";
    }

    @Override
    public String saveFormat() {
        return String.join(Task.delimiter,
                            super.saveFormat(),
                            this.at.toString());
    }
}
