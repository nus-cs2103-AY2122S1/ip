package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** A class that handles task-mark-as-done command. */
public class DoneCommand extends Command {

    private final int taskNum;

    /**
     * A constructor for class DoneCommand.
     *
     * @param taskNum The number of the to-be-marked-as-done task.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Execute the task-mark-as-done command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(this.taskNum - 1);
        task.markAsDone();
        storage.save(tasks);

        String response = String.format("%s%s",
                "Nice! I've marked this task as done:\n\t",
                task);

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
