public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getSize()) {
            throw new DukeException("This entry does not exist!");
        }
        tasks.doneTask(index);
        ui.printTemplate( "Nice! I've marked this task as done:\n" + "  " + tasks.getTask(index));
        storage.update(tasks);
    }
}
