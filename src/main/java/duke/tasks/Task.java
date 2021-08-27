package tasks;

import main.StorageElement;

public abstract class Task {
    protected String taskIcon;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(StorageElement storageElement) {
        this.taskIcon = storageElement.getTaskIcon();
        this.description = storageElement.getDescription();
        this.isDone = storageElement.getDone();
    }

    public static Task of(StorageElement storageElement) {
        if (storageElement.getTaskIcon().equals("T")) {
            return new Todo(storageElement);
        } else if (storageElement.getTaskIcon().equals("D")) {
            return new Deadline(storageElement);
        } else if (storageElement.getTaskIcon().equals("E")) {
            return new Event(storageElement);
        } else {
            return null;
        }
    }

    abstract public StorageElement getStorageElement();

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
}