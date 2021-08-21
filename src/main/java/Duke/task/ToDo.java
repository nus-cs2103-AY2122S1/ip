package duke.task;

import java.time.LocalDate;

public class ToDo extends Task {

    public ToDo(String details) {
        super(details);
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
