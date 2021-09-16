package command;

import exception.InvalidValue;
import storage.Storage;
import task.TaskList;

/**
 * DoneCommand will mark as task as completed when executed.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int i) {
        this.taskNumber = i;
    }

    /**
     * Execute the given command returned by parse method.
     * Each command class will have its own interaction with Ui, TaskList and Storage
     *
     * @param tasks the TaskList loaded from storage.
     * @param storage accesses the file location in local storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidValue {
        try {
            tasks.completeTask(taskNumber);
            storage.write(tasks.getTaskList());
            return String.format("Nice! I've marked this task as done:\n"
                    + "%s\n", tasks.getTask(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidValue();
        }
    }
}
