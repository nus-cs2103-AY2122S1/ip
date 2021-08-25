package commands;

import core.TaskList;
import tasks.Event;

public class EventCommand extends Command {
    private Event eventTask;

    public EventCommand(String taskName, String time) {
        eventTask = new Event(taskName, time);
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(eventTask);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
