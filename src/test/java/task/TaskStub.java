package task;

import java.time.LocalDate;

public class TaskStub extends Task {

    public TaskStub(String input, boolean done) {
        super(input, done);
    }

    /**
     * Toggle task completion, simple execution
     *
     * @return Current done status
     */
    public boolean toggleDone() {
        isDone = !isDone;
        return isDone;
    }

    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    /**
     * Returns a string representation to be saved
     *
     * @return string to be saved in text file
     */
    public String saveString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    public boolean isDate(LocalDate date) {
        return false;
    }
}
