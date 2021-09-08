package bobbybot.commands;

import bobbybot.exceptions.BobbyException;
import bobbybot.tasks.Task;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

/**
 * Represents command for DONE
 */
public class DoneCommand extends Command {
    private final int taskNumToMarkAsDone;
    public DoneCommand(int toMarkAsDone) {
        this.taskNumToMarkAsDone = toMarkAsDone;
    }
    /**
     * Executes command
     *
     * @param tasks   task list
     * @param ui      ui
     * @param storage storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskCompleted = tasks.getTask(taskNumToMarkAsDone - 1);
            tasks.markAsDone(taskNumToMarkAsDone);
            storage.save(tasks);
            response = "Nice! I've marked this task as done:\n" + " " + taskCompleted;
        } catch (BobbyException e) {
            System.out.println(e.getMessage());
        }
    }
}
