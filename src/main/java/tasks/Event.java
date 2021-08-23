package tasks;

import utils.StorageParser;

public class Event extends Task {
    protected String at;

    public Event(String description, String time) {
        super(description);
        this.taskIcon = "E";
        this.at = time;
    }

    public Event(StorageParser storageParser) {
        super(storageParser);
        this.at = storageParser.getTime();
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at +")";
    }

    @Override
    public String saveFormat() {
        return String.join(Task.delimiter,
                            super.saveFormat(),
                            this.at);
    }
}
