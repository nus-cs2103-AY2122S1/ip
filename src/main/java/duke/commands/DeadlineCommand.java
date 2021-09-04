package duke.commands;

import duke.gui.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

/**
 * Encapsulates the information of a DeadlineCommand object that contains a Deadline.
 */
public class DeadlineCommand extends Command {
    private final Deadline task;

    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        taskList.addTask(this.task);

        return Ui.printAddTaskMessage(this.task, taskList.getTaskCount());
    }
}
