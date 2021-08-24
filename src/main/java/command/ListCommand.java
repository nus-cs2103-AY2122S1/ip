package command;
import task.*;
import duke.*;

/**
 * ListCommand represents a list command.
 *
 * @author Ho Wen Zhong
 */
public class ListCommand extends Command {

    /**
     * Calls showResponse with task list as message.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.list();
        ui.showResponse(message);
    }

    /**
     * Returns false to indicate ListCommand does not exit the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
