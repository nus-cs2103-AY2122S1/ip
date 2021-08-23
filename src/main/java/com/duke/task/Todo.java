package com.duke.task;

import java.io.IOException;

public class Todo extends TaskList {
    public Todo(String description, boolean isExisting) {
        super(description);
        if (!isExisting) {
            try {
                file.saveTask(this); // Saves task to hard disk
                userInterface.taskAdded(this);
            } catch (IOException e) {
                userInterface.fileNotFoundWarning();
            }
        }
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
