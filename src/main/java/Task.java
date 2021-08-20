import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected final String description;
    protected boolean done;

    public Task(String input, boolean done) {
        description = input;
        this.done = done;
    }

    /**Toggles completion of task
     *
     * @return New status of task
     */
    public boolean toggleDone() {
        done = !done;
        return done;
    }

    /**
     * String representation of Task
     * @return task display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    /**
     * The string representation of Task to be used for saving
     * @return Save string
     */
    abstract String saveString();
    abstract boolean isDate(LocalDate date);
}
