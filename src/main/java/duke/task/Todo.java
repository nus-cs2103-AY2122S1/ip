package duke.task;

import java.time.LocalDate;

import duke.CommandList;

public class Todo extends Task {


    public Todo(String value) {
        super(value);
    }

    @Override
    public LocalDate getTime() {
        return null;
    }

    @Override
    public CommandList getType() {
        return CommandList.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
