package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Event;

public class EventCommand extends Command {
    private Event eventTask;

    public EventCommand(String taskName, String time) {
        eventTask = new Event(taskName, time);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(eventTask);
        storage.addTasksToFile(taskList);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
