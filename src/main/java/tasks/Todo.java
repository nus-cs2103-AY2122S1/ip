package tasks;

import utils.StorageParser;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.taskIcon = "T";
    }

    public Todo(StorageParser storageParser) {
        super(storageParser);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveFormat() {
        return String.join(Task.delimiter,
                this.taskIcon,
                isDone ? "1" : "0",
                this.description);
    }
}
