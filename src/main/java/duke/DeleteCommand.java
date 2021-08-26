package duke;
public class DeleteCommand extends Command {

    private String command;

    public DeleteCommand(String command) {
        super(command);
        this.command = command;

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        ui.showLine();
        ui.removeTask(tasks.get(Integer.valueOf(command.split(" +")[1])-1));
        ui.numberOfTasks(tasks);
        storage.editFileAll(tasks);
    }
}
