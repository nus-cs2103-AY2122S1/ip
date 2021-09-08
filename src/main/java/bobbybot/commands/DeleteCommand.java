package bobbybot.commands;

import java.io.IOException;

import bobbybot.tasks.Task;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class DeleteCommand extends Command {

    private int taskNumToDelete;

    public DeleteCommand(int taskNumToDelete) {
        this.taskNumToDelete = taskNumToDelete;
    }
    /**
     * Executes command
     *
     * @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @return
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumToDelete < 1 || taskNumToDelete > tasks.getTasks().size()) {
            this.response = "Invalid delete command! Task number: " + taskNumToDelete + "does not exist\n"
                    + "Use [list] to see available tasks!";
        }
        Task taskToDelete = tasks.getTask(taskNumToDelete - 1);
        tasks.deleteTask(taskNumToDelete);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Could not save tasks to database!\n");
            e.printStackTrace();
        }
        String response = "Noted. I've removed this task: " + taskToDelete
                + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
        this.response = response;
    }
}
