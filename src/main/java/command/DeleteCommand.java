package command;
import task.*;
import duke.*;

/**
 * DeleteCommand represents a delete command.
 *
 * @author Ho Wen Zhong
 */
public class DeleteCommand extends Command {

    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Deletes task from TaskList, calls showResponse with response,
     * saves TaskList to file.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(deleteIndex);
        tasks.delete(deleteIndex);
        int numOfTasks = tasks.size();

        String response = respond(task, numOfTasks);
        ui.showResponse(response);
        // storage
        storage.save(tasks);
    }

    /**
     * Returns false to indicate DeleteCommand does not exit the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns delete message.
     *
     * @param task
     * @param numOfTasks
     * @return Delete message.
     */
    public String respond(Task task, int numOfTasks) {
        String response = "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numOfTasks
                + " tasks in the list.";
        return response;
    }
}
