/**
 * This is a DoneCommand class that extends Command.
 */
public class DoneCommand extends Command {

    public DoneCommand(int index) {
        super("done", index);
    }


    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws DukeFileException, TaskIsCompleteException, TaskNotFoundException {
        if (taskList.getSize() - 1 >= this.index && this.index >= 0) {
            taskList.markTask(this.index, store, ui);
        } else {
            throw new TaskNotFoundException(this.index + 1);
        }

    }
}
