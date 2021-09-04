package duke.commands;

import duke.gui.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

/**
 * Encapsulates the information of a EventCommand object that contains an Event.
 */
public class EventCommand extends Command {
    private final Event task;

    public EventCommand(Event task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        taskList.addTask(this.task);

        return Ui.printAddTaskMessage(this.task, taskList.getTaskCount());
    }
}
