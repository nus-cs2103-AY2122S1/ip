package commands;

import java.time.LocalDate;

import tasks.Deadline;

public class AddDeadlineCommand extends AddTaskCommand {

    public AddDeadlineCommand(String desc, boolean isDone, LocalDate deadline) {
        super(new Deadline(desc, isDone, deadline));
    }

}
