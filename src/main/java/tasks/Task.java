package tasks;

import utils.StorageParser;

public class Task {
    public static String delimiter = " | ";
    public static String delimiter_regex = " \\| ";
    protected String taskIcon;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(StorageParser storageParser) {
        this.taskIcon = storageParser.getTaskIcon();
        this.description = storageParser.getDescription();
        this.isDone = storageParser.getDone();
    }

    public static Task of(StorageParser storageParser) {
        if (storageParser.getTaskIcon().equals("T")) {
            return new Todo(storageParser);
        } else if (storageParser.getTaskIcon().equals("D")) {
            return new Deadline(storageParser);
        } else if (storageParser.getTaskIcon().equals("E")) {
            return new Event(storageParser);
        } else {
            return null;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String saveFormat() {
        return String.join(Task.delimiter,
                        this.taskIcon,
                        isDone ? "1" : "0",
                        this.description);
    }
}