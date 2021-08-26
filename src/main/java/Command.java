public abstract class Command {

    private String command;

    public Command(String command) {
        this.command = command;

    }

    public abstract void execute(TaskList tasks,
                                 Ui ui, Storage storage) throws DukeException;
}
