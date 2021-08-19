/**
 * This is a DeleteCommand class that extends Command.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(int index){
        super("delete", index);
    }

    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws DukeFileException, TaskNotFoundException {
        if (taskList.getSize() - 1 >= this.index && this.index >= 0) {
            taskList.deleteTask(this.index, store, ui);
        } else {
            throw new TaskNotFoundException(this.index + 1);
        }
    }
}
