package duke.logic.command;

import duke.logic.tasks.Event;
import duke.logic.tasks.TaskList;

public class EventCommand extends Command {
    private Event task;

    public EventCommand(Event task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.addTask(task);
    }
}
