package command;

import exception.InvalidValue;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * DoneCommand will mark as task as completed when executed.
 */
public class DoneCommand extends Command {

    private int taskNumber;
    private final boolean EXIT = false;

    public DoneCommand(int i) {
        this.taskNumber = i;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidValue {
        try {
            tasks.completeTask(taskNumber);
            storage.write(tasks.getTaskList());
            System.out.printf("\tNice! I've marked this task as done:\n" + "\t%s\n", tasks.getTask(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidValue();
        }
    }

    public boolean isExit() {
        return EXIT;
    }
}
