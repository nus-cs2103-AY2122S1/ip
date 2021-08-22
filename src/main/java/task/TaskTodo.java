import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskTodo extends Task {

    public TaskTodo(String description, boolean done) {
        super(description, done);
    }

    /**
     * String representation of to-do
     *
     * @return to-do display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[T]" + checkBox + description;
    }

    @Override
    public String saveString() {
        return "T" + '\t'
                + (this.done ? "1" : "0") + '\t'
                + this.description;
    }

    @Override
    public boolean isDate(LocalDate date) {
        return false;
    }
}
