package com.duke.task;

import java.io.IOException;

/**
 * Represents a possible type of task in our task list, known as a Todo.
 * This task is the most basic, with no added functionality to support deadline and
 * timing.
 */
public class Todo extends TaskList {

    /**
     * Creates an instance of a Todo class, a subclass of our task list class.
     *
     * @param description the description of our task to be logged
     * @param isExisting the boolean that distinguishes between an existing task loaded from
     *                   the task file or a newly added task during program execution
     */
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

    /**
     * Returns the String representation of a Todo task class.
     *
     * @return the String representation of a Todo task class
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
