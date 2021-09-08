package bobbybot.commands;

import bobbybot.exceptions.BobbyException;
import bobbybot.tasks.Task;
import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class DeleteCommand extends Command {

    private final int taskNumToDelete;

    public DeleteCommand(int taskNumToDelete) {
        this.taskNumToDelete = taskNumToDelete;
    }
    /**
     * Executes command
     *  @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @param contacts
     */
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        if (taskNumToDelete < 1 || taskNumToDelete > tasks.getTasks().size()) {
            this.response = "Invalid delete command! Task number: " + taskNumToDelete + " does not exist\n"
                    + "Use [list] to see available tasks!";
            return;
        }

        try {
            Task taskToDelete = tasks.getTask(taskNumToDelete - 1);
            tasks.deleteTask(taskNumToDelete);
            storage.save(tasks);
            this.response = "Noted. I've removed this task: " + taskToDelete
                    + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
        } catch (BobbyException e) {
            System.out.println(e.getMessage());
        }
    }
}
