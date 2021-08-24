public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskDone = tasks.getTask(index);
            taskDone.markAsDone();
            ui.displayCompletedMessage(taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t List number out of range, please enter a valid number\n");
        }
    }
}
