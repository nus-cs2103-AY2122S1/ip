package command;

import exception.InvalidValue;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    private int taskNumber;
    private final boolean EXIT = false;

    public DeleteCommand(int i) {
        this.taskNumber = i;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage)  throws InvalidValue {
        try {
            Task deletedTask = tasks.deleteTask(taskNumber);
            System.out.printf("\tNoted. I've removed this task:\n" +
                    "\t%s\n" +
                    "\tNow you have %d tasks in the list.\n", deletedTask, tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidValue();
        }
    }

    public boolean isExit() {
        return EXIT;
    }
}
