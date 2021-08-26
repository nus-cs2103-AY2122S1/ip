import java.time.LocalDateTime;

class EventCommand extends Command {
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