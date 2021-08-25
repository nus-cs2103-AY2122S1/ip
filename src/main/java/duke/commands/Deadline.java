package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime  by){
        super(description, isDone, 'D');
        this.by = by;
    }

    @Override
    public String checkStatus() {
        return super.checkStatus() + " " + this.showDeadline();
    }

    public String showDeadline(){
        return String.format("(by: %s)", by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
    }

    @Override
    public String toString(){
        return super.toString() + String.format("|%s", by);
    }
}
