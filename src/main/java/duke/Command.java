package duke;
public abstract class Command {

    private String command;

    public Command(String command) {
        this.command = command;

    }
    public boolean isBye() {
        return false;
    }
    public abstract void execute(TaskList tasks,
                                 Ui ui, Storage storage) throws DukeException;
}
