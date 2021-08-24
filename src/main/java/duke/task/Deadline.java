package duke.task;

import duke.task.Task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toDataString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, this.description, this.date);
    }
    
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %d %d)", super.toString(), date.getMonth(), date.getDayOfMonth(), 
                date.getYear());
    }
}
