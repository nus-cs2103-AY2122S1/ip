package duke.command;

import duke.TaskList;
import duke.task.Event;
import java.time.LocalDate;

/**
 * Represents an event command.
 */
public class EventCommand extends Command {

    private Event event;

    public EventCommand(String input) {
        String[] parsed = input.split(" ", 2)[1].split(" /at ");
        assert parsed.length == 2 : "Invalid command entered.";
        String task = parsed[0];
        LocalDate time = LocalDate.parse(parsed[1]);
        this.event = new Event(task, time);
    }

    /**
     * Returns the result of the execution of the event command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the event command.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.event);
    }
}
