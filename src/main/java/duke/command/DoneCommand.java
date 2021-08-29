package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Constructs a DoneCommand object.
     *
     * @param taskNumber Index of the task item.
     */
    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isTaskExists(this.taskNumber)) {
            Task task = tasks.getTask(this.taskNumber - 1);
            task.markAsDone();
            storage.writeToFile(tasks.getAllTasks());
            ui.display("Nice! This task is marked as done: \n" + "      " + task);
        } else {
            ui.display("This task does not exist! Please try again.");
        }
    }

}
