package duke.commands;

import duke.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

public class EventCommand extends Command {
    private final Event task;

    public EventCommand(Event task) {
        this.task = task;
    }

    @Override
    public void executeCommand(TaskList taskList) {
        taskList.addTask(task);
        Ui.printAddTaskMessage(task, taskList.getTaskCount());
    }
}
