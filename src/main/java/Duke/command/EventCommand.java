package Duke.command;

import Duke.TaskList;
import Duke.task.Event;
import java.time.LocalDate;

public class EventCommand extends Command {
    private Event event;

    public EventCommand(String input) {
        String[] parsed = input.split(" ", 2)[1].split(" /at ");
        String task = parsed[0];
        LocalDate time = LocalDate.parse(parsed[1]);
        this.event = new Event(task, time);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.event);
    }
}
