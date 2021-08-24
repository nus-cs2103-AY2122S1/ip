public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.deleteTask(index);
            ui.displayDeleteMessage(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t List number out of range, please enter a valid number\n");
        }
    }
}
