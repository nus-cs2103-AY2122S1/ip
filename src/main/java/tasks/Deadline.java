package tasks;

import utils.StorageParser;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String time) {
        super(description);
        this.taskIcon = "D";
        this.by = time;
    }

    public Deadline(StorageParser storageParser) {
        super(storageParser);
        this.by = storageParser.getTime();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by +")";
    }

    @Override
    public String saveFormat() {
        return String.join(Task.delimiter,
                            super.saveFormat(),
                            this.by);
    }
}
