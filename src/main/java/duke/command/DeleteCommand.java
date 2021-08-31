package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** A class that handles task-deletion command. */
public class DeleteCommand extends Command {

    private final int taskNum;

    /**
     * A constructor for class DeleteCommand.
     *
     * @param taskNum The number of the to-be-deleted task.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Execute the task-deletion command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(this.taskNum - 1);
        tasks.delete(this.taskNum - 1);
        storage.save(tasks);

        String response = String.format("%s%s%s",
                "Noted. I've deleted this task:\n",
                "\t " + task,
                "\nNow you have " + tasks.getTaskNum() + " tasks in the list.");

        return response;
    }

    /**
     * Return a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
