package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isTaskExists(this.taskNumber)) {
            Task taskDel = tasks.getTask(this.taskNumber - 1);
            tasks.deleteTask(this.taskNumber- 1);
            ui.display("Gotchu mate. I've removed this task: \n" + "      " + taskDel + "\n    Now you have "
                    + tasks.length() + (tasks.length() <= 1 ? " task" : " tasks") + " in the list.");
            storage.writeToFile(tasks.getAllTasks());
        } else {
            ui.display("This task does not exist! Please try again.");
        }
    }

}
