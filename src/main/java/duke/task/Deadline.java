package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description, "deadline");
        this.by = by;
    }

    @Override
    public String saveTaskFormat(){
        return "D" + super.saveTaskFormat() + String.format("|%s", by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}