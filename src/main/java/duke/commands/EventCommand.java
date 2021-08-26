package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Event;

/**
 * Adds a new event task to the task list.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Creates a command to add an event task.
     *
     * @param taskDescription Description of the event task.
     * @param at The start time of the event.
     * @param end The end time of the event if applicable, else null.
     */
    public EventCommand(String taskDescription, LocalDateTime at, LocalDateTime end) {
        event = new Event(taskDescription, at, end);
    }

    /**
     * Adds the new event task to the task list.
     *
     * @return The execution result.
     */
    @Override
    public CommandResult execute() {
        taskList.add(event);
        return new CommandResult("I've added:\n  " + event);
    }
}