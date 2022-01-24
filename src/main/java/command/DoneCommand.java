package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;

/**
 * DoneCommand represents a done command.
 *
 * @author Ho Wen Zhong
 */
public class DoneCommand extends Command {

    private int doneIndex;

    /**
     * Constructs a DoneCommand object.
     *
     * @param doneIndex index of the task to be marked as done.
     */
    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    /**
     * Sets task as done in TaskList, calls showResponse with response,
     * saves TaskList to file.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(doneIndex);

        Task task = tasks.get(doneIndex);
        String response = respond(task);
        String result = ui.showResponse(response);
        // storage
        storage.save(tasks);

        return result;
    }

    /**
     * Returns false to indicate DoneCommand does not exit the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns done message.
     *
     * @param task Task to be done.
     * @return Done message.
     */
    public String respond(Task task) {
        String response = "Nice! I've marked this task as done:\n"
                + task.toString();
        return response;
    }
}
