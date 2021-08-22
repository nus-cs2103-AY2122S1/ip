package duke.commands;

import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskList;


public class DeadlineCommand extends Command {
    private final Deadline task;

    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    @Override
    public void executeCommand(TaskList taskList) {
        taskList.addTask(this.task);
        Ui.printAddTaskMessage(this.task, taskList.getTaskCount());
    }
}
