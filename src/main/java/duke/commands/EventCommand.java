package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Event;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String taskDescription, LocalDateTime at, LocalDateTime end) {
        event = new Event(taskDescription, at, end);
    }

    @Override
    public CommandResult execute() {
        taskList.add(event);
        return new CommandResult("I've added:\n  " + event);
    }
}