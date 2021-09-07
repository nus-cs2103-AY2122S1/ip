package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private boolean isDone;
    private String Todo;

    /**
     * Constructor for a Task class
     *
     * @param Todo
     */
    public Task(String Todo) {
        this.isDone = false;
        this.Todo = Todo;
    }

    /**
     * method to mark a task object as completed
     */
    public void completed() {
        this.isDone = true;
    }

    /**
     * method to return the description inputted when creating a Task object
     *
     * @return description inputted when creating a Task object
     */
    public String getString() {
        return this.Todo;
    }

    /**
     * toString method which returns string representation of a task
     *
     * @return description of the task, and whether it is completed or not, in a specific format
     */
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.getString();
        } else {
            return "[ ] " + this.getString();
        }
    }

    /**
     * toString method which returns string representation of a task, to be printed in a file
     *
     * @return description of the task, and whether it is completed or not, in a specific format
     */
    public String toStringConvert() {
        if (this.isDone) {
            return "[X] " + this.getString();
        } else {
            return "[ ] " + this.getString();
        }
    }

    /**
     * method to check for whether a task has been completed
     *
     * @return boolean for whether a task is completed
     */
    public boolean isCompleted() {
        return this.isDone;
    }


    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.parse("0000-01-01 0000", DateTimeFormatter.ofPattern("uuuu-MM-dd kkmm"));
    }

}