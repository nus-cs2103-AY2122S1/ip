package task;

import java.time.LocalDate;

/**
 * Stub of a standard task.
 */
public class TaskStub extends Task {

    public TaskStub(String input, boolean done) {
        super(input, done);
    }

    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    /**
     * Returns a string representation to be saved.
     *
     * @return string to be saved in text file.
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
