package bobbybot.commands;

import bobbybot.tasks.Task;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNumToMarkAsDone;
    public DoneCommand(int toMarkAsDone) {
        this.taskNumToMarkAsDone = toMarkAsDone;
    }
    /**
     * Executes command and get response
     *
     * @param tasks   task list
     * @param ui      ui
     * @param storage storage
     * @return
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        Task taskCompleted = tasks.getTask(taskNumToMarkAsDone - 1);
        tasks.markAsDone(taskNumToMarkAsDone);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Could not save tasks to database!\n");
            e.printStackTrace();
        }
        return "Nice! I've marked this task as done:\n" + " " + taskCompleted;
    }
}
