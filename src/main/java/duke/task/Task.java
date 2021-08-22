package duke.task;

import java.time.LocalDate;

public class Task {
    private boolean isDone = false;
    private final String description;

    public Task(String description) {
        this.description = description;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        char indicator = ' ';
        if (isDone) {
            indicator = 'X';
        }
        return "[" + indicator + "] " + this.description;
    }

    public String saveString() {
        char indicator = '0';
        if (isDone) {
            indicator = '1';
        }
        return indicator + "|" + this.description;
    }
}